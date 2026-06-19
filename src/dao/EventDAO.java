package dao;

import config.Koneksi;
import model.EventModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    private Connection conn;

    public EventDAO() {
        conn = Koneksi.getKoneksi(); // Mengambil koneksi database otomatis
    }

    // Fungsi Login Check Aman dari SQL Injection via PreparedStatement
    public boolean loginCheck(String username, String password) {
        String sql = "SELECT * FROM tb_admin WHERE username = ? AND password = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Mengembalikan true jika username & password cocok di DB
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // C - Create (Tambah Data)
    public boolean insert(EventModel ev) {
        String sql = "INSERT INTO tb_event (nama, organizer, event, status_bayar) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ev.getNama());
            ps.setString(2, ev.getOrganizer());
            ps.setString(3, ev.getEvent());
            ps.setString(4, ev.getStatusBayar());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // R - Read (Ambil Semua Data untuk JTable)
    public List<EventModel> getAll() {
        List<EventModel> list = new ArrayList<>();
        String sql = "SELECT * FROM tb_event ORDER BY no DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new EventModel(
                    rs.getInt("no"), rs.getString("nama"),
                    rs.getString("organizer"), rs.getString("event"),
                    rs.getString("status_bayar")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // U - Update (Ubah Data)
    public boolean update(EventModel ev) {
        String sql = "UPDATE tb_event SET nama = ?, organizer = ?, event = ?, status_bayar = ? WHERE no = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ev.getNama());
            ps.setString(2, ev.getOrganizer());
            ps.setString(3, ev.getEvent());
            ps.setString(4, ev.getStatusBayar());
            ps.setInt(5, ev.getNo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // D - Delete (Hapus Data)
    public boolean delete(int no) {
        String sql = "DELETE FROM tb_event WHERE no = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, no);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Fitur Pencarian Data secara Realtime
    public List<EventModel> search(String keyword) {
        List<EventModel> list = new ArrayList<>();
        String sql = "SELECT * FROM tb_event WHERE nama LIKE ? OR organizer LIKE ? OR event LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new EventModel(
                    rs.getInt("no"), rs.getString("nama"),
                    rs.getString("organizer"), rs.getString("event"),
                    rs.getString("status_bayar")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}