/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.sql.*;
//import BUS.busKiemKe;
import DAO.accessDB;
//import DAO.DateTimeNow;
//import DAO.daoKho;
//import DAO.daoKiemKe;
//import DAO.daoLoaiSanPham;
//import DAO.daoPhieuKiemKeKho;
//import DAO.daoSanPham;
//import DAO.daoXuatKho;
//import DTO.LoaiSanPham;
//import DTO.NhanVien;
//import DTO.PhieuKiemKeKho;
//import DTO.SanPham;
//import DTO.TaiKhoan;
//import GROUP.ThongTinKhoHienTai;
//import GROUP.ThongTinKiemKe;
import com.mysql.cj.protocol.Resultset;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.lang.*;
import java.lang.reflect.GenericDeclaration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Xoan Tran
 */
public class f_kiemKe extends javax.swing.JFrame {

    /**
     * Creates new form fKiemKe
     */
    public int id_nv;
//    public ArrayList<ThongTinKhoHienTai> DuLieuMau;
//    public ArrayList<ThongTinKhoHienTai> DanhSach;
    public long count, SoTrang, Trang = 1;

    public f_kiemKe() {
//<<<<<<< HEAD
//        DanhSach = daoKho.getInstance().getListThongTinKhoHienTai();
//        DuLieuMau = DanhSach;
//=======
//        DanhSach = busKiemKe.getInstance().DanhSachThongTinKhoHienTai();
//        DuLieuMau = DanhSach ; 

        initComponents();
        setIcon();
        build();
    }


//    public fKiemKe(int id_nv) {
//        this.id_nv = id_nv;
//        DanhSach = daoKho.getInstance().getListThongTinKhoHienTai();
//        DuLieuMau = DanhSach;
//    }

    public f_kiemKe(int id_nv)
    {
        this.id_nv=id_nv;
        System.err.println("KIEMKE con "+f_DangNhap.id_nv);
        initComponents();
        setIcon();
        build();
    }


    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/rsz_iconsieuthi.png")));
    }

    public void build() {
//         String sql="SELECT ID_KV, Ten_SP,(SELECT COUNT(*) AS Lo_Ton FROM Lo_San_Pham WHERE ID_KV=Khu_Vuc.ID_KV), ID_NV FROM (San_Pham JOIN Lo_San_Pham ON San_Pham.ID_SP=Lo_San_Pham.ID_SP) AS TG JOIN Khu_Vuc ON TG.ID_KV=Khu_Vuc.ID_KV";
        String sql="select phieu_kiem_ke_kv.ID_Kiem_Ke, phieu_kiem_ke_kv.id_kv,so_lo_Ton,so_lo_hao_mon,ten_nv,thoi_gian_kiem,tinh_trang,phieu_kiem_ke_kv.id_nv from phieu_kiem_ke_kv join nhan_vien on phieu_kiem_ke_kv.ID_NV=nhan_vien.ID_NV ;";
        accessDB.getInstance().open();
        ResultSet rs = accessDB.getInstance().excuteQuery(sql);
        DefaultTableModel model = (DefaultTableModel) jTableKhoHienTai.getModel();

        // Gán giá trị cho bang
        int i=0;
        try {
            while(rs.next()){
                model.setValueAt(rs.getInt(1), i, 0);
                model.setValueAt(rs.getString(2), i, 1);
                String tmp = "select ten_sp from san_pham join lo_san_pham on san_pham.id_sp=lo_san_pham.id_sp join phieu_nhap on lo_san_pham.id_phieu_nhap=phieu_nhap.id_phieu_nhap where id_nv="+rs.getInt(8)+";";
                ResultSet tenSP = accessDB.getInstance().excuteQuery(tmp);
                try {
                    tenSP.next();
                    model.setValueAt(tenSP.getString(1), i, 2); 
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
//                
                model.setValueAt(rs.getInt(3), i, 3);
                model.setValueAt(rs.getInt(4), i, 4);
                model.setValueAt(rs.getString(5), i, 5); 
                model.setValueAt(rs.getString(6), i, 6);
                model.setValueAt(rs.getString(7), i, 7);
                i++;
                
            }
        }
        catch (SQLException ex){
            ex.getErrorCode();
            ex.printStackTrace();
        }
          
    }

//    public void showComboboxLoaiSanPham() {
//        jComboBoxLoaiSP.removeAllItems();
//        ArrayList<LoaiSanPham> arr = daoLoaiSanPham.getInstance().getListLoaiSanPham();
//        jComboBoxLoaiSP.addItem("Tất cả");
//        for (int i = 0; i < arr.size(); i++) {
//            jComboBoxLoaiSP.addItem(arr.get(i).ten_loai_sp);
//        }
//    }
//
//    public void NhanVienDangNhap() {
//        if (id_nv != 0) {
//            TaiKhoan tk = DAO.daoTaiKhoan.getInstance().getTaiKhoan(id_nv);
//            NhanVien nv = DAO.daoTaiKhoan.getInstance().getNhanVien(tk.id_nv);
//            jComboBoxNhanVien2.addItem(nv.ten_nv);
//            jComboBoxNhanVien2.addItem("Thông tin");
//            jComboBoxNhanVien2.addItem("Thoát");
//        } else {
//            jComboBoxNhanVien2.addItem("Chưa đăng nhập");
//        }
//    }
//
//    public void listDanhSachKhoHienTai(ArrayList<ThongTinKhoHienTai> arr) {
//        DefaultTableModel model = (DefaultTableModel) jTableKhoHienTai.getModel();
//        while (jTableKhoHienTai.getRowCount() > 0) {
//            model.removeRow(0);
//        }
//
//        arr.stream().forEach((item) -> {
//            PhieuKiemKeKho phieu = daoPhieuKiemKeKho.getInstance().getPhieuKiemKeKho(item.id_kho);
//            //int _tongsp = item.so_luong_lo * item.sl_san_pham;
//            if (phieu == null) {
//                model.addRow(new Object[]{item.id_kho, item.ten_sp, item.hsd, item.sl_san_pham, "", "","", "Đầy đủ"});
//            }
//            if(phieu!=null && item.so_luong_lo==phieu.sl_hao_mon)
//            {
//                model.addRow(new Object[]{item.id_kho, item.ten_sp, item.hsd,item.sl_san_pham, phieu.sl_thuc_te , "",DateTimeNow.getIntance().FormatDateViewTable(phieu.thoi_gian), "Đầy đủ"});
//            }
//            if(phieu!=null && item.so_luong_lo!=phieu.sl_hao_mon) {
//                model.addRow(new Object[]{item.id_kho, item.ten_sp, item.hsd, item.sl_san_pham ,phieu.sl_thuc_te, phieu.sl_hao_mon,DateTimeNow.getIntance().FormatDateViewTable(phieu.thoi_gian), "Hao hụt"});
//            }
//        });
//    }
//
//    public void FindList() {
//        //String DuLieu = jTextFieldTimKiem.getText();
//        this.DanhSach = DAO.daoKho.getInstance().FindListKhoHienTai(DuLieuMau, jTextFieldTimKiem.getText());
//        if (DanhSach.isEmpty()) {
//            JOptionPane.showMessageDialog(null,
//                    "Không có dữ liệu kiểm kê kho",
//                    "Lỗi",
//                    JOptionPane.ERROR_MESSAGE);
//            build();
//        } else {
//            this.count = this.DanhSach.size();
//            jLabelKetQua.setText("Có tổng cộng " + count + " kết quả");
//            if (count % 20 == 0) {
//                SoTrang = count / 20;
//            } else {
//                SoTrang = count / 20 + 1;
//            }
//            jLabelSoTrang.setText("1/" + SoTrang);
//            jLabelTrang.setText("1");
//            ArrayList<ThongTinKhoHienTai> table = DAO.daoKho.getInstance().get20KhoHienTai(DanhSach, 1);
//            listDanhSachKhoHienTai(table);
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableKhoHienTai = new javax.swing.JTable();
        jButtonTaiLai = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Kiểm kê");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 204, 153));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Phần mềm quản lý kho");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("siêu thị ");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTableKhoHienTai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_Kiem_Ke", "ID KV", "Tên Sản Phẩm", "Lô Tồn", "Số Lô Hao Mòn", "Nhân viên kiểm", "Ngày kiểm kê", "Tình trạng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableKhoHienTai.setRowHeight(30);
        jTableKhoHienTai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableKhoHienTaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableKhoHienTai);
        if (jTableKhoHienTai.getColumnModel().getColumnCount() > 0) {
            jTableKhoHienTai.getColumnModel().getColumn(0).setMinWidth(80);
            jTableKhoHienTai.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTableKhoHienTai.getColumnModel().getColumn(0).setMaxWidth(80);
            jTableKhoHienTai.getColumnModel().getColumn(1).setMinWidth(100);
            jTableKhoHienTai.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTableKhoHienTai.getColumnModel().getColumn(1).setMaxWidth(100);
            jTableKhoHienTai.getColumnModel().getColumn(2).setMinWidth(270);
            jTableKhoHienTai.getColumnModel().getColumn(2).setPreferredWidth(270);
            jTableKhoHienTai.getColumnModel().getColumn(2).setMaxWidth(270);
            jTableKhoHienTai.getColumnModel().getColumn(3).setMinWidth(70);
            jTableKhoHienTai.getColumnModel().getColumn(3).setPreferredWidth(70);
            jTableKhoHienTai.getColumnModel().getColumn(3).setMaxWidth(70);
            jTableKhoHienTai.getColumnModel().getColumn(5).setMinWidth(200);
            jTableKhoHienTai.getColumnModel().getColumn(5).setPreferredWidth(200);
            jTableKhoHienTai.getColumnModel().getColumn(5).setMaxWidth(200);
            jTableKhoHienTai.getColumnModel().getColumn(6).setPreferredWidth(80);
        }

        jButtonTaiLai.setText("Tải lại");
        jButtonTaiLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTaiLaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jButtonTaiLai)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jButtonTaiLai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-waste-48.png"))); // NOI18N
        jButton3.setText("Xóa");
        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton3.setIconTextGap(20);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-plus-48.png"))); // NOI18N
        jButton4.setText("Thêm");
        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton4.setIconTextGap(15);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(jLabel2)
                .addContainerGap(963, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableKhoHienTaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableKhoHienTaiMouseClicked
            
        int selectedRowIndex = jTableKhoHienTai.getSelectedRow();
        int id_kho = jTableKhoHienTai.getValueAt(selectedRowIndex, 0).hashCode();
        int lo_ton = jTableKhoHienTai.getValueAt(selectedRowIndex, 3).hashCode();
//        jTextField_id_kho.setText(id_kho + "");
//        jTextField_Lo_ton.setText(lo_ton + "");
//        jTextFieldThoiGian.setText(DAO.DateTimeNow.getIntance().DateView);
        
//        if (evt.getClickCount() == 1 && !evt.isConsumed()) {
//            evt.consume();
//            
//            //System.out.print(id_kho);
//        }
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            //evt.consume();
            
            JFrame Xem = new f_chiTietKiemKe((int)jTableKhoHienTai.getValueAt(selectedRowIndex, 0).hashCode());
            Xem.setLocationRelativeTo(null);
            Xem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Xem.setVisible(true);
            //System.out.print("Nhap dup chuot");
        }
//        tungggggg
        
    }//GEN-LAST:event_jTableKhoHienTaiMouseClicked

    private void jButtonTaiLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTaiLaiActionPerformed
////        DuLieuMau = daoKho.getInstance().getListThongTinKhoHienTai();
            build();
////        DanhSach = DuLieuMau;
////        this.count = this.DanhSach.size();
////        jLabelKetQua.setText("Có tổng cộng " + count + " kết quả");
////        if (count % 20 == 0) {
////            SoTrang = count / 20;
////        } else {
////            SoTrang = count / 20 + 1;
////        }
////        jLabelSoTrang.setText("1/" + SoTrang);
////        jLabelTrang.setText("1");
////        ArrayList<ThongTinKhoHienTai> table = DAO.daoKho.getInstance().get20KhoHienTai(DanhSach, 1);
////        listDanhSachKhoHienTai(table);
//        String sql="SELECT * FROM Phieu_Kiem_Ke_KV";
//        accessDB.getInstance().open();
//        ResultSet rs = accessDB.getInstance().excuteQuery(sql);
//        DefaultTableModel model = (DefaultTableModel) jTableKhoHienTai.getModel();
//        int i=0;
//        try {
//            while(rs.next()){
//                model.setValueAt(rs.getInt(1), i, 0);
//                model.setValueAt(rs.getString(2), i, 1);
//                String tmp1 = rs.getString(2);
//                System.out.println(tmp1);
//                String Ten_SP="SELECT San_Pham.Ten_SP,Khu_Vuc.ID_KV  FROM San_Pham INNER JOIN Lo_San_Pham ON San_Pham.ID_SP = Lo_San_Pham.ID_SP INNER JOIN Khu_Vuc ON Lo_San_Pham.ID_KV = Khu_Vuc.ID_KV WHERE Khu_Vuc.ID_KV='"+tmp1+"';";
//                ResultSet SP = accessDB.getInstance().excuteQuery(Ten_SP);
////                try {
////                    int kt=0;
////                    while(SP.next()){
////                        System.out.println(SP.getString(1));
////                        kt=1;
////                    }
////                    if(kt==0) System.out.println("co cai nit");
////                } catch (Exception e) {
////                    System.out.println("loi");
////                    e.printStackTrace();
////                }
//                SP.next();
//                model.setValueAt(SP.getString(1), i, 2);
//                model.setValueAt(rs.getInt(3), i, 3);
//                model.setValueAt(rs.getInt(4), i, 4);
//                int tmp2=rs.getInt(5);
//                String Ten_NV = "SELECT TEN_NV FROM Nhan_Vien WHERE Nhan_Vien.ID_NV = "+tmp2;
//                ResultSet NV = accessDB.getInstance().excuteQuery(Ten_NV);
//                NV.next();
//                model.setValueAt(NV.getString(1), i, 5); 
//                model.setValueAt(rs.getDate(6), i, 6);
//                model.setValueAt(rs.getString(7), i, 7);
//                i++;
//                
//            }
//        }
//        catch (SQLException ex){
//            ex.getErrorCode();
//            ex.printStackTrace();
//        }
    }//GEN-LAST:event_jButtonTaiLaiActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        f_themKiemKe themKiemKe = new f_themKiemKe();
        themKiemKe.setLocationRelativeTo(null);
        themKiemKe.setVisible(true);
        themKiemKe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int[] selectedRows = jTableKhoHienTai.getSelectedRows();

        if (selectedRows.length > 0) {
            // Xóa các dòng từ model của JTable
            DefaultTableModel model = (DefaultTableModel) jTableKhoHienTai.getModel();
            for (int i = selectedRows.length - 1; i >= 0; i--) {
                int id = (int) model.getValueAt(selectedRows[i], 0); 
                String sql = "DELETE FROM Phieu_Kiem_Ke_KV WHERE ID_Kiem_Ke = " + id;
                accessDB.getInstance().executeUpdate(sql);
                model.removeRow(selectedRows[i]);
            }
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(f_kiemKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(f_kiemKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(f_kiemKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(f_kiemKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.err.println("KIEM KE "+f_DangNhap.id_nv);
                new f_kiemKe(f_DangNhap.id_nv).setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonTaiLai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTableKhoHienTai;
    // End of variables declaration//GEN-END:variables
}
