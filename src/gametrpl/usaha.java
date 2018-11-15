/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ROG
 */
public class usaha {

    String namaUsaha;
    int id;
    int modal, penghasilan, operasional, letak;

    int uPenghasilan, uOperasional, bPenghasilan, bOperasional, bSpesial;
    boolean boostP = false, boostO = false, boostS = false;
    int min, max;
    int persenUp, persenO, persenUpS, persenOS;
    int tempP, tempO, tempS;
    int p, o, s;

    public usaha(String namaUsaha, int modal, int penghasilan, int operasional, int uPenghasilan, int uOperasional, int bPenghasilan, int bOperasional, int bSpesial, int persenUp, int persenO, int persenUpS, int persenOS, int p, int o, int s, int min, int max) {
        this.namaUsaha = namaUsaha;
        this.modal = modal;
        this.penghasilan = penghasilan;
        this.operasional = operasional;
        this.uPenghasilan = uPenghasilan;
        this.uOperasional = uOperasional;
        this.bPenghasilan = bPenghasilan;
        this.bOperasional = bOperasional;
        this.bSpesial = bSpesial;
        this.persenUp = persenUp;
        this.persenO = persenO;
        this.p = p;
        tempP = p;
        this.o = o;
        tempO = o;
        this.s = s;
        tempS = s;
        this.persenUpS = persenUpS;
        this.persenOS = persenOS;
        this.min = min;
        this.max = max;
    }

    public usaha(int id, int id_pemain, String namaUsaha, int letak_usaha, int modal, int penghasilan, int operasional, int uPenghasilan, int uOperasional, int bPenghasilan, int bOperasional, int bSpesial, int persenUp, int persenO, int persenUpS, int persenOS, int p, int o, int s, int min, int max) {
        this.id = id;
        this.namaUsaha = namaUsaha;
        this.letak = letak_usaha;
        this.modal = modal;
        this.penghasilan = penghasilan;
        this.operasional = operasional;
        this.uPenghasilan = uPenghasilan;
        this.uOperasional = uOperasional;
        this.bPenghasilan = bPenghasilan;
        this.bOperasional = bOperasional;
        this.bSpesial = bSpesial;
        this.persenUp = persenUp;
        this.persenO = persenO;
        this.p = p;
        tempP = p;
        this.o = o;
        tempO = o;
        this.s = s;
        tempS = s;
        this.persenUpS = persenUpS;
        this.persenOS = persenOS;
        this.min = min;
        this.max = max;
    }

    public usaha() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLetak() {
        return letak;
    }

    public void setLetak(int letak) {
        this.letak = letak;
    }

    public int getPersenUpS() {
        return persenUpS;
    }

    public void setPersenUpS(int persenUpS) {
        this.persenUpS = persenUpS;
    }

    public int getPersenOS() {
        return persenOS;
    }

    public void setPersenOS(int persenOS) {
        this.persenOS = persenOS;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getPersenUp() {
        return persenUp;
    }

    public void setPersenUp(int persenUp) {
        this.persenUp = persenUp;
    }

    public int getPersenO() {
        return persenO;
    }

    public void setPersenO(int persenO) {
        this.persenO = persenO;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getO() {
        return o;
    }

    public void setO(int o) {
        this.o = o;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public int getTempP() {
        return tempP;
    }

    public void setTempP(int tempP) {
        this.tempP = tempP;
    }

    public int getTempO() {
        return tempO;
    }

    public void setTempO(int tempO) {
        this.tempO = tempO;
    }

    public int getTempS() {
        return tempS;
    }

    public void setTempS(int tempS) {
        this.tempS = tempS;
    }

    public boolean isBoostP() {
        return boostP;
    }

    public void setBoostP(boolean boostP) {
        this.boostP = boostP;
    }

    public boolean isBoostO() {
        return boostO;
    }

    public void setBoostO(boolean boostO) {
        this.boostO = boostO;
    }

    public boolean isBoostS() {
        return boostS;
    }

    public void setBoostS(boolean boostS) {
        this.boostS = boostS;
    }

    public int getModal() {
        return modal;
    }

    public void setModal(int modal) {
        this.modal = modal;
    }

    public int getPenghasilan() {
        return penghasilan;
    }

    public void setPenghasilan(int penghasilan) {
        this.penghasilan = penghasilan;
    }

    public int getOperasional() {
        return operasional;
    }

    public void setOperasional(int operasional) {
        this.operasional = operasional;
    }

    public String getNamaUsaha() {
        return namaUsaha;
    }

    public void setNamaUsaha(String namaUsaha) {
        this.namaUsaha = namaUsaha;
    }

    public int getuPenghasilan() {
        return uPenghasilan;
    }

    public void setuPenghasilan(int uPenghasilan) {
        this.uPenghasilan = uPenghasilan;
    }

    public int getuOperasional() {
        return uOperasional;
    }

    public void setuOperasional(int uOperasional) {
        this.uOperasional = uOperasional;
    }

    public int getbPenghasilan() {
        return bPenghasilan;
    }

    public void setbPenghasilan(int bPenghasilan) {
        this.bPenghasilan = bPenghasilan;
    }

    public int getbOperasional() {
        return bOperasional;
    }

    public void setbOperasional(int bOperasional) {
        this.bOperasional = bOperasional;
    }

    public int getbSpesial() {
        return bSpesial;
    }

    public void setbSpesial(int bSpesial) {
        this.bSpesial = bSpesial;
    }

    public boolean save(int id_pemain, int letak_usaha) {
        String query = "INSERT INTO `usaha`"
                + "(`id_user`, `nama_usaha`, `modal`, `penghasilan`, `operasional`, `u_penghasilan`, `u_operasional`,"
                + " `b_penghasilan`, `b_operasional`, `b_spesial`, `persen_up`, `persen_o`, `persen_ups`, `persen_os`, `lama_p`, "
                + "`lama_o`, `lama_s`, `volatility_min`, `volatility_max`,`letak_usaha`) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setInt(1, id_pemain);
            statement.setString(2, getNamaUsaha());
            statement.setInt(3, getModal());
            statement.setInt(4, getPenghasilan());
            statement.setInt(5, getOperasional());
            statement.setInt(6, getuPenghasilan());
            statement.setInt(7, getuOperasional());
            statement.setInt(8, getbPenghasilan());
            statement.setInt(9, getbOperasional());
            statement.setInt(10, getbSpesial());
            statement.setInt(11, getPersenUp());
            statement.setInt(12, getPersenO());
            statement.setInt(13, getPersenUpS());
            statement.setInt(14, getPersenOS());
            statement.setInt(15, getP());
            statement.setInt(16, getO());
            statement.setInt(17, getS());
            statement.setInt(18, getMin());
            statement.setInt(19, getMax());
            statement.setInt(20, letak_usaha);

            int row = statement.executeUpdate();
            if (row > 0) {
                return true;
            }
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<usaha> loadUsaha(int id_pemain) {
        List<usaha> dataUsaha = new ArrayList<>();
        usaha u;
        String query = "SELECT * FROM `usaha` where id_user=? order by letak_usaha";

        try {
            PreparedStatement statement = koneksi.getConnection().prepareStatement(query);
            statement.setInt(1, id_pemain);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                u = new usaha(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getInt(10),
                        rs.getInt(11),
                        rs.getInt(12),
                        rs.getInt(13),
                        rs.getInt(14),
                        rs.getInt(15),
                        rs.getInt(16),
                        rs.getInt(17),
                        rs.getInt(18),
                        rs.getInt(19),
                        rs.getInt(20),
                        rs.getInt(21)
                );
                dataUsaha.add(u);
            }
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataUsaha;
    }

}
