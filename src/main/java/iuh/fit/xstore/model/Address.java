package iuh.fit.xstore.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Address entity - Lưu trữ địa chỉ của người dùng
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Số nhà/tòa
    @Column(name = "street_number")
    private String streetNumber;

    // Tên đường
    @Column(name = "street_name")
    private String streetName;

    // Phường/Xã
    private String ward;

    // Quận/Huyện
    private String district;

    // Thành phố/Tỉnh
    private String city;

    // Là địa chỉ mặc định không?
    @Column(name = "is_default")
    @Builder.Default
    private boolean isDefault = false;

    /**
     * Tạo full address string từ các thành phần
     */
    public String getFullAddress() {
        StringBuilder sb = new StringBuilder();
        if (streetNumber != null && !streetNumber.isEmpty()) {
            sb.append(streetNumber).append(" ");
        }
        if (streetName != null && !streetName.isEmpty()) {
            sb.append(streetName).append(", ");
        }
        if (ward != null && !ward.isEmpty()) {
            sb.append(ward).append(", ");
        }
        if (district != null && !district.isEmpty()) {
            sb.append(district).append(", ");
        }
        if (city != null && !city.isEmpty()) {
            sb.append(city);
        }
        return sb.toString().trim();
    }
}
