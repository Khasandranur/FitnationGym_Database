package com.example.fitnationgym.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.fitnationgym.model.Jadwal;
import com.example.fitnationgym.model.Admin;
import com.example.fitnationgym.model.Klien;
import com.example.fitnationgym.model.Pelatih;
@Controller
public class FitnessController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUser() {
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String login(@ModelAttribute(name = "Admin") Admin admin, Model model) {
        String username = admin.getUsername();
        String password = admin.getPassword();
        try {
            String sql = "SELECT * FROM admin WHERE username = ?";
            Admin asli = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Admin.class), username);
            model.addAttribute("asli", asli);
            String userAsli = asli.getUsername();
            String passAsli = asli.getPassword();
            if (password.equals(passAsli)) {
                return "redirect:/main";
            }
        } catch (EmptyResultDataAccessException e) {
            // TODO: handle exception
            model.addAttribute("invalidCredentials", true);
        }
        model.addAttribute("invalidCredentials", true);
        return "login";
    }

    @GetMapping("/main")
    public String index(Model model) {
        String sql = "SELECT * FROM klien WHERE DELETED='n'";
        List<Klien> klienList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Klien.class));
        model.addAttribute("klien", klienList);
        return "main";
    }

    @GetMapping("/jadwal")
    public String jadwalList(Model model) {
        String sql = "SELECT * FROM jadwal";
        List<Jadwal> jadwalList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Jadwal.class));
        model.addAttribute("jadwal", jadwalList);
        return "jadwal";
    }

    @GetMapping("/pelatih")
    public String pelatihList(Model model) {
        String sql = "SELECT * FROM pelatih";
        List<Pelatih> pelatihList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Pelatih.class));
        model.addAttribute("pelatih", pelatihList);
        return "pelatih";
    }

    @GetMapping("/search")
    public String hasilsearch(@PathParam("nama") String nama, Model model) {
        String sql = "SELECT * FROM klien WHERE LOWER(nama) LIKE CONCAT(CONCAT ('%', ?), '%')";
        List<Klien> klienList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Klien.class), nama);
        model.addAttribute("klien", klienList);
        return ("search");
    }

    @GetMapping("/softdelete/{id_klien}")
    public String softDelete(@PathVariable("id_klien") String id_klien) {
        String sql = "UPDATE klien SET DELETED = 'y' WHERE id_klien = ?";
        jdbcTemplate.update(sql, id_klien);
        return "redirect:/main";
    }

    @GetMapping("/trash")
    public String recycle(Model model) {
        String sql = "SELECT * FROM klien WHERE DELETED='y'";
        List<Klien> klienList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Klien.class));
        model.addAttribute("klien", klienList);
        return "trash";
    }

    @GetMapping("/restore/{id_klien}")
    public String restore(@PathVariable("id_klien") String id_klien) {
        String sql = "UPDATE klien SET DELETED = 'n' WHERE id_klien = ?";
        jdbcTemplate.update(sql, id_klien);
        return "redirect:/main";
    }

    @GetMapping("/harddeleteklien/{id_klien}")
    public String hardDelete(@PathVariable("id_klien") String id_klien) {
        String sql = "DELETE FROM klien WHERE id_klien = ?";
        jdbcTemplate.update(sql, id_klien);
        return "redirect:/main";
    }

    @GetMapping("/addklien")
    public String addWedding(Model model) {
        return "addklien";
    }

    @RequestMapping(value ="/addklien")
    public String addWedding(Klien klien, Model model) {
        String sql = "INSERT INTO klien VALUES (?, ?, ?, ?, ?, ?, 'n')";
        jdbcTemplate.update(sql,
                klien.getId_klien(), klien.getEmail(),
                klien.getGender(), klien.getUsia(), klien.getId_jadwal(),
                klien.getNama());
        return "redirect:/main";
    }

    @GetMapping("/editklien/{id_klien}")
    public String editWedding(@PathVariable("id_klien") String id_klien, Model model) {
        String sql = "SELECT * FROM klien WHERE id_klien = ?";
        Klien klien = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Klien.class), id_klien);
        model.addAttribute("klien", klien);
        return "editklien";
    }

    @PostMapping("/editklien")
    public String editWedding(Klien klien) {
        String sql = "UPDATE klien SET id_klien=?, email = ?, gender = ?, usia = ?, id_jadwal = ?, nama = ? WHERE id_klien = ?";
        jdbcTemplate.update(sql, klien.getId_klien(), klien.getEmail(), klien.getGender(), klien.getUsia(),klien.getId_jadwal(),klien.getNama(), klien.getId_klien());
        return "redirect:/main";
    }

    @GetMapping("/detailklien/{id_klien}")
    public String klienDetail(@PathVariable("id_klien") String id_klien, Model model) {
        String sql = "SELECT * FROM klien JOIN jadwal ON (klien.id_jadwal = jadwal.id_jadwal) WHERE id_klien = ?";
        Klien klien = jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(Klien.class), id_klien);
        Jadwal jadwal = jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(Jadwal.class), id_klien);
        model.addAttribute("klien", klien);
        model.addAttribute("jadwal", jadwal);
        return "detailklien";
    }

}
