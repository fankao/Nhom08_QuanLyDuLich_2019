
--Chèn cơ sở dữ liệu địa danh du lịch
use [Nhom08_DeTai06_13A_2019]

INSERT INTO [dbo].[DiaDanh] 
VALUES ('DD002',N'Nghệ An'),
('DD003',N'Hạ Long'),
('DD004',N'Sapa'),
('DD005',N'Ninh Bình'),
('DD006',N'Hòa bình'),
('DD007',N'Phan Thiết'),
('DD008',N'Nha Trang'),
('DD009',N'Đà Lạt'),
('DD0010',N'Tây Nguyên'),
('DD0011',N'Đà Nẳng - Hội An'),
('DD0012',N'Tuy Hòa - Quy Nhơn - Quảng Ngãi'),
('DD0013',N'Huế'),
('DD0014',N'Phú Quốc'),
('DD0015',N'Miền Tây'),
('DD0016',N'Côn Đảo'),
('DD0017',N'Hồ Tràm')

--Chèn chức vụ
INSERT INTO [dbo].[ChucVu] 
VALUES ('001',N'Nhân Viên'),
('002',N'Quản lí')

INSERT INTO [dbo].[ChucVu] 
VALUES ('003',N'ADMIN')

--Chèn tài khoản
INSERT INTO [dbo].[TaiKhoan]
VALUES ('NV001','NV001','01','NV001'),
('NV002','NV002','01','NV002'),
('QL001','QL001','02','QL001'),
('ADMIN','ADMIN','03','ADMIN')


--Chèn địa chỉ
INSERT INTO [dbo].[DiaChi]
VALUES ('DC001',N'04 Nguyễn Văn Bảo, P4, Q.Gò Vấp, TP.HCM'),
('DC002',N'24 Hạnh Thông, P3, Q.Gò Vấp, TP.HCM'),
('DC003',N'123/12 Dương Quảng Hàm, P7, Q.Gò Vấp, TP.HCM'),
('DC004',N'234 Nguyễn Oanh, P12, Q.Gò Vấp, TP.HCM')


--Chèn thông tin nhân viên
INSERT INTO [dbo].[NhanVien]
VALUES ('NV001',N'Phan Thanh Trí','039447329','03/12/1999','0121778393','DC001','001','NV001'),
('NV002',N'Võ Gia Hưng','039402695','08/01/1999','01216658740','DC002','001','NV002'),
('QL001',N'Nguyễn Minh Chiến','039861357','12/23/1999','0756412368','DC003','002','QL001'),
('ADMIN',N'Nguyễn Thanh Tùng','039581635','04/12/1999','0965874512','DC004','003','ADMIN')

delete from NhanVien
delete from [dbo].[ChucVu]
delete from [dbo].[DiaChi]
delete from [dbo].[DiaDanh]
