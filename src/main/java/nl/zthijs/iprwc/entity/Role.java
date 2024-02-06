package nl.zthijs.iprwc.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
public class Role {

    @Id
    @Column(nullable = false, length = 20, unique = true)
    private String id;

    @Column(nullable = false, length = 100)
    @JsonIgnore
    private String description;


    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<User> users;

    public Role(String id) { this.id = id; }

    public Role(String id, String description) {
        this.id = id;
        this.description = description;
    }

}
