/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;
import java.lang.*;

/**
 *
 * @author nghia
 */
public class NhanVien {
    public int ID_NV;
    public String TEN_NV;
    public String SDT;
    public String CCCD;
    public String NGAY_SINH;
    public byte[] Hinh_Anh;
   
    public NhanVien(int ID_NV,String TEN_NV,String SDT, String CCCD, String NGAY_SINH, byte[] Hinh_Anh ) {
        this.ID_NV =ID_NV;
        this.TEN_NV = TEN_NV;
        this.SDT = SDT;
        this.CCCD = CCCD;
        this.NGAY_SINH = NGAY_SINH;
        this.Hinh_Anh = Hinh_Anh;
         }
    
}
