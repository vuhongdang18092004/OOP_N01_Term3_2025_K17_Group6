# OOP_N01_Term3_2025_K17_Group6

---


# Báo Cáo Bài Tập Lớn: Ứng Dụng Web CarAmbulance

## Xây dựng ứng dụng quản lý hệ thống xe cứu thương

### Yêu cầu:

- **Giao diện Java Spring Boot**:
  - Ứng dụng được xây dựng sử dụng Spring Boot 3.5.0, Java 24, tích hợp Thymeleaf cho giao diện web và Spring Data JPA để quản lý dữ liệu.
  - Cung cấp giao diện web tại `http://localhost:8080` để truy cập các chức năng chính, cùng với API RESTful để thực hiện CRUD.

- **Có chức năng quản lý xe cứu thương (Ambulance)**:
  - **Thêm, sửa, xóa xe cứu thương**:
    - Thêm xe cứu thương mới với các thuộc tính: biển số, loại xe, hình ảnh, sức chứa, thiết bị y tế (máy khử rung, số giường, số ống tiêm, danh sách thiết bị), trạng thái (AVAILABLE, IN_USE, MAINTENANCE).
    - Sửa thông tin xe cứu thương hiện có.
    - Xóa xe cứu thương khỏi hệ thống.
  - **Liệt kê thông tin về xe cứu thương**:
    - Hiển thị danh sách tất cả xe cứu thương.
    - Lọc danh sách xe cứu thương theo trạng thái (e.g., chỉ hiển thị xe có trạng thái AVAILABLE).
    - Endpoint: `GET /api/ambulances` và `GET /api/ambulances/available`.

- **Có chức năng quản lý bệnh viện (Hospital)**:
  - **Thêm, sửa, xóa bệnh viện**:
    - Thêm bệnh viện mới với các thuộc tính: tên, địa chỉ, số liên lạc.
    - Sửa thông tin bệnh viện hiện có.
    - Xóa bệnh viện khỏi hệ thống.
  - Endpoint: `POST /api/hospitals`, `GET /api/hospitals`, `PUT /api/hospitals`, `DELETE /api/hospitals/{id}`.

- **Có chức năng gán xe cứu thương (Ambulance) cho bệnh viện (Hospital)**:
  - Tạo chuyến đi (Trip) để gán xe cứu thương cho bệnh viện đích, bao gồm thông tin: ID xe cứu thương, tài xế, bác sĩ, bệnh nhân, địa điểm đón, bệnh viện đích, trạng thái chuyến đi (SCHEDULED, IN_PROGRESS, COMPLETED).
  - Endpoint: `POST /api/trips`.

- **Dữ liệu được lưu trữ trong database MySQL**:
  - Thay vì lưu trữ xuống file nhị phân, dữ liệu được lưu trong database MySQL (`carambulance`) để đảm bảo hiệu quả và tính bền vững.
  - Các lớp liên quan đến **Ambulance**, **Hospital**, và **Trip** sử dụng Spring Data JPA để đọc/ghi dữ liệu vào database.
  - Cấu hình trong `application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/carambulance
    spring.datasource.username=root
    spring.datasource.password=your_mysql_password
    spring.jpa.hibernate.ddl-auto=update
    ```

- **Dữ liệu trong bộ nhớ**:
  - Dữ liệu được quản lý trong bộ nhớ dưới dạng các Collection như `List<Ambulance>`, `List<Hospital>`, `List<Trip>` thông qua Spring Data JPA và các repository (`AmbulanceRepository`, `HospitalRepository`, `TripRepository`).
  - Ví dụ: `AmbulanceRepository.findAll()` trả về `List<Ambulance>`.

- **Chức năng bổ sung (tùy chọn)**:
  - Giao diện web Thymeleaf tại `http://localhost:8080` để truy cập nhanh các danh sách (xe cứu thương sẵn sàng, bệnh viện, chuyến đi).
  - Phân quyền mô phỏng:
    - **Admin**: Toàn quyền CRUD trên tất cả đối tượng.
    - **Guest**: Chỉ được gọi xe cứu thương (`POST /api/trips`) và xem xe sẵn sàng (`GET /api/ambulances/available`).
  - Tự động thêm dữ liệu mẫu khi khởi động ứng dụng thông qua `DataInitializer.java`.

---

## Nội dung 02: Sơ đồ Class Diagram

Sơ đồ Class Diagram mô tả cấu trúc các lớp trong dự án, bao gồm các đối tượng chính (`Ambulance`, `Hospital`, `Trip`), các lớp chung (`ObjectGeneral`, `ObjectList`), và mối quan hệ giữa chúng.

- **Link ảnh**: [Class Diagram](https://i.imgur.com/sample-class-diagram.png)
- **Hiển thị ảnh**:
  ![Class Diagram](https://i.imgur.com/sample-class-diagram.png)

**Giải thích**:
- `ObjectGeneral`: Lớp trừu tượng chứa thuộc tính chung (`id`, `name`).
- `Ambulance`, `Hospital`, `Trip`: Kế thừa từ `ObjectGeneral` (trừ `Trip` do không có thuộc tính `name`).
- `ObjectList`: Lớp generic xử lý CRUD chung cho các đối tượng.
- Các repository (`AmbulanceRepository`, `HospitalRepository`, `TripRepository`) tương tác với database.
- Các service (`AmbulanceService`, `HospitalService`, `TripService`) xử lý logic nghiệp vụ.
- Các controller (`AmbulanceController`, `HospitalController`, `TripController`, `HomeController`) cung cấp API và giao diện web.

---

## Nội dung 03: Sơ đồ Behavioural Diagram

### Sequence Diagram
Sequence Diagram minh họa quy trình tạo một chuyến đi (`POST /api/trips`), bao gồm tương tác giữa các thành phần: Client, Controller, Service, Repository, và Database.

- **Link ảnh**: [Sequence Diagram](https://i.imgur.com/sample-sequence-diagram.png)
- **Hiển thị ảnh**:
  ![Sequence Diagram](https://i.imgur.com/sample-sequence-diagram.png)

**Giải thích**:
- Client gửi yêu cầu `POST /api/trips` với JSON của `Trip`.
- `TripController` nhận yêu cầu và gọi `TripService.createTrip()`.
- `TripService` gọi `TripRepository.save()` để lưu vào database.
- Database trả về `Trip` đã lưu, được chuyển ngược lại qua các lớp và trả về Client dưới dạng JSON.

---

## Nội dung 04: Cập nhật code cho CRUD và kiểm thử

### CRUD cho 03 đối tượng (Ambulance, Hospital, Trip)

Dự án đã triển khai đầy đủ CRUD cho **Ambulance**, **Hospital**, và **Trip** thông qua các controller, service, và repository. Dưới đây là tóm tắt:

1. **Ambulance**:
   - **Controller**: `AmbulanceController.java` (artifact ID: `fe03c6b7-5344-4154-abc6-1ed25dc913a9`).
   - **Endpoints**:
     - `POST /api/ambulances`: Tạo xe cứu thương.
     - `GET /api/ambulances/{id}`: Lấy xe theo ID.
     - `PUT /api/ambulances`: Cập nhật xe.
     - `DELETE /api/ambulances/{id}`: Xóa xe.
     - `GET /api/ambulances/available`: Lấy xe sẵn sàng.
   - **Service**: `AmbulanceService.java` (artifact ID: `7e7e7b15-e17b-4e5c-9a2a-3c7a1b5b5e6e`) với try-catch.
   - **Repository**: `AmbulanceRepository.java`.

2. **Hospital**:
   - **Controller**: `HospitalController.java` (artifact ID: `730eb565-0149-4c0b-8db3-31789e22d90f`).
   - **Endpoints**:
     - `POST /api/hospitals`: Tạo bệnh viện.
     - `GET /api/hospitals`: Lấy danh sách bệnh viện.
     - `GET /api/hospitals/{id}`: Lấy bệnh viện theo ID.
     - `PUT /api/hospitals`: Cập nhật bệnh viện.
     - `DELETE /api/hospitals/{id}`: Xóa bệnh viện.
   - **Service**: `HospitalService.java` (artifact ID: `56b75eac-6fda-44b8-a9c5-cf9607927415`).
   - **Repository**: `HospitalRepository.java`.

3. **Trip**:
   - **Controller**: `TripController.java` (artifact ID: `8ff639d9-e1f8-49bb-9b91-bfc4f40c3ac6`).
   - **Endpoints**:
     - `POST /api/trips`: Tạo chuyến đi.
     - `GET /api/trips`: Lấy danh sách chuyến đi.
     - `GET /api/trips/{id}`: Lấy chuyến đi theo ID.
     - `PUT /api/trips`: Cập nhật chuyến đi.
     - `DELETE /api/trips/{id}`: Xóa chuyến đi.
   - **Service**: `TripService.java` (artifact ID: `2ea308fb-d186-47bc-891f-d4a417668394`).
   - **Repository**: `TripRepository.java`.

**Mã nguồn mẫu (CRUD trong ObjectList.java)**:
```java
package com.project.CarAmbulance.common;

import java.util.ArrayList;
import java.util.List;

public class ObjectList<T extends ObjectGeneral> {
    private List<T> objects = new ArrayList<>();

    public void create(T object) {
        try {
            objects.add(object);
        } catch (Exception e) {
            throw new RuntimeException("Error creating object: " + e.getMessage());
        }
    }

    public T read(int id) {
        try {
            return objects.stream()
                    .filter(obj -> obj.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Object not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error reading object: " + e.getMessage());
        }
    }

    public void update(T object) {
        try {
            int index = objects.indexOf(objects.stream()
                    .filter(obj -> obj.getId() == object.getId())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Object not found")));
            objects.set(index, object);
        } catch (Exception e) {
            throw new RuntimeException("Error updating object: " + e.getMessage());
        }
    }

    public void delete(int id) {
        try {
            objects.removeIf(obj -> obj.getId() == id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting object: " + e.getMessage());
        }
    }

    public List<T> getAll() {
        try {
            return new ArrayList<>(objects);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving objects: " + e.getMessage());
        }
    }
}
```

### Kiểm thử (Test)

Kiểm thử CRUD được thực hiện trong `TestObjectList.java` (artifact ID: `5088be92-aeba-477d-8f6d-38c72dea76f0`) cho 3 đối tượng: **Ambulance**, **Hospital**, **Trip**.

**Mã nguồn kiểm thử mẫu**:
```java
package com.project.CarAmbulance.common;

import com.project.CarAmbulance.model.Ambulance;
import com.project.CarAmbulance.model.Hospital;
import com.project.CarAmbulance.model.Trip;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestObjectList {

    @Test
    public void testAmbulanceCRUD() {
        ObjectList<Ambulance> ambulanceList = new ObjectList<>();
        Ambulance ambulance = new Ambulance();
        ambulance.setId(1);
        ambulance.setName("Ambulance 1");
        ambulance.setLicensePlate("XYZ-123");
        ambulance.setStatus("AVAILABLE");

        // Create
        ambulanceList.create(ambulance);
        assertEquals(1, ambulanceList.getAll().size());

        // Read
        Ambulance retrieved = ambulanceList.read(1);
        assertEquals("XYZ-123", retrieved.getLicensePlate());

        // Update
        ambulance.setStatus("IN_USE");
        ambulanceList.update(ambulance);
        assertEquals("IN_USE", ambulanceList.read(1).getStatus());

        // Delete
        ambulanceList.delete(1);
        assertEquals(0, ambulanceList.getAll().size());
    }

    @Test
    public void testHospitalCRUD() {
        ObjectList<Hospital> hospitalList = new ObjectList<>();
        Hospital hospital = new Hospital();
        hospital.setId(1);
        hospital.setName("City Hospital");

        // Create
        hospitalList.create(hospital);
        assertEquals(1, hospitalList.getAll().size());

        // Read
        Hospital retrieved = hospitalList.read(1);
        assertEquals("City Hospital", retrieved.getName());

        // Update
        hospital.setName("General Hospital");
        hospitalList.update(hospital);
        assertEquals("General Hospital", hospitalList.read(1).getName());

        // Delete
        hospitalList.delete(1);
        assertEquals(0, hospitalList.getAll().size());
    }

    @Test
    public void testTripCRUD() {
        ObjectList<Trip> tripList = new ObjectList<>();
        Trip trip = new Trip();
        trip.setId(1);
        trip.setAmbulanceId(1);
        trip.setStatus("SCHEDULED");

        // Create
        tripList.create(trip);
        assertEquals(1, tripList.getAll().size());

        // Read
        Trip retrieved = tripList.read(1);
        assertEquals("SCHEDULED", retrieved.getStatus());

        // Update
        trip.setStatus("COMPLETED");
        tripList.update(trip);
        assertEquals("COMPLETED", tripList.read(1).getStatus());

        // Delete
        tripList.delete(1);
        assertEquals(0, tripList.getAll().size());
    }
}
```

**Chạy kiểm thử**:
- Chạy lệnh:
  ```powershell
  mvn test
  ```
- Kết quả mong đợi:
  ```
  [INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.123 s - in com.project.CarAmbulance.common.TestObjectList
  ```

**Ghi chú**:
- Các test sử dụng `ObjectList` để kiểm tra CRUD generic.
- Các service test (`AmbulanceServiceTest.java`, `HospitalServiceTest.java`, `TripServiceTest.java`) kiểm tra CRUD với database MySQL.
- Tất cả phương thức trong `ObjectList` và các service sử dụng try-catch để xử lý lỗi, đáp ứng **Yêu cầu 3.2**.

---

## Hướng dẫn chạy ứng dụng

1. **Cài đặt môi trường**:
   - Java 24, Maven, MySQL.
   - Tạo database: `CREATE DATABASE carambulance; CREATE DATABASE carambulance_test;`.

2. **Cấu hình**:
   - Cập nhật `src/main/resources/application.properties` và `src/test/resources/application.properties` với thông tin MySQL.

3. **Chạy ứng dụng**:
   ```powershell
   mvn clean install
   mvn spring-boot:run
   ```

4. **Kiểm tra kết quả**:
   - Truy cập `http://localhost:8080` để xem giao diện web.
   - Sử dụng PowerShell để gọi API:
     ```powershell
     Invoke-WebRequest -Uri "http://localhost:8080/api/ambulances/available" -Method Get | Select-Object -ExpandProperty Content
     ```
   - Chạy test: `mvn test`.

---

## Kết luận

Ứng dụng **CarAmbulance** đáp ứng đầy đủ các yêu cầu ban đầu, bao gồm quản lý xe cứu thương, bệnh viện, chuyến đi, và tích hợp giao diện web. Các chức năng CRUD được triển khai với xử lý lỗi try-catch, kiểm thử đầy đủ cho 3 đối tượng, và dữ liệu được lưu trữ trong MySQL. Các sơ đồ Class Diagram và Sequence Diagram cung cấp cái nhìn tổng quan về cấu trúc và luồng xử lý của hệ thống.


---