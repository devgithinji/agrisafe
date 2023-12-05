package com.agrisafe.common.model.auth;

import com.agrisafe.common.model.User;
import com.agrisafe.common.model.farmer.Farm;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "farmers")
public class Farmer extends User {

    @OneToMany(mappedBy = "farmer")
    private List<Farm> farms;

}
