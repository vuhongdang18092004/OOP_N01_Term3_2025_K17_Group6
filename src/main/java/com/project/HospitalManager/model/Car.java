package com.project.HospitalManager.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Car {
    public Car(int id_car) {
		this.idCar=id_car;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCar;

    @Column(columnDefinition = "nvarchar(50) not null")
	private String nameCar;

	@Column(columnDefinition = "nvarchar(4000)")
	private String overviewCar;

	private int price;
	private int promotionalPrice;
	
	@Column(columnDefinition = "int default 0")
	private int oldPromotionalPrice;

	@Column(columnDefinition = "nvarchar(20)", unique = true)
	private String licensePlates;

	private int modelYear;

    // trang thai xe
	private int status;

	private int fuel; // 0 - Xăng, 1 - Dầu, 2 - Điện, 3 - Hybrid

	// fuel for 100km
	private float fuelFor100km;

	// Quãng đường đón tối đa
	private int maximumDeliveryDistance;
	private int deliveryFeeFor1Km;

	// km tối đa trên ngày
	private int maximumKilometersperDay;
	private int limitCrossingfee1Km;

	@Column(columnDefinition = "nvarchar(2000)")
	private String polyUseCar;

	@Column(columnDefinition = "nvarchar(1000)")
	private String addressCar;

	@Column(columnDefinition = "nvarchar(200)")
	private String avatarCar;

	@Column(columnDefinition = "nvarchar(2000)")
	private String imageCar;

	
	// số ghế ngồi
	private int numberOfSeats;
    // số bác sĩ
    private int doctorSeat;
    // số y tá
    private int nurseSeat;
    // bluetooth
	private boolean bluetooth;
	// camerea hành trình
	private boolean dashCamera;
	// camera lùi
	private boolean reverseCamera;
	// camera 360
	private boolean camera360;
	// cam cập lề
	private boolean parkingCamera;
	// cảm biến ám suât lốp
	private boolean tpms;
	// cảnh báo tốc độ
	private boolean speedWarning;
	// gps
	private boolean gpsLocator;
	// Cửa sổ trời
	private boolean sunroof;
	// màn hình dvd
	private boolean dvdScreen;
	// tự lái hoặc có tài
	private boolean driver;
	// map
	private boolean maps;
	// lốp dự phòng
	private boolean spareTire;
	// usb
	private boolean usb;
	// cảnh báo va chạm
	private boolean impactSensor;
	// túi khí an toàn
	private boolean airbags;
	// số sàn hoặc tự dộng
	private boolean manualTransmissionCar;
    // số xe giường bệnh nhân
    private int patientBed;
    // thiết bị đo nhịp tim
    private boolean heartRateMonitor;
    // thiết bị đo huyết áp
    private boolean bloodPressureMonitor;
    // thiết bị đo nhiệt độ
    private boolean temperatureMonitor;
    // thiết bị đo nồng độ oxy
    private boolean oxygenSaturationMonitor;
    // bình oxy
    private boolean oxygenCylinder;
    // dụng cụ y tế cơ bản
    private boolean medicalEquipment;

	private Date createDate;
	private Date updateDate;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_brand")
	@JsonIgnore
	private BrandCar brandCar;
	
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private List<Booking> booking;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	@JsonIgnore
	private User user;
}
