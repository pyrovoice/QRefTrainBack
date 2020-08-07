package com.quidditchreftraining.qreftrain.dao;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "national_governing_body")
@Getter
@Setter
@NoArgsConstructor
public class NationalGoverningBody {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String abbreviation;
    private String location;

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof NationalGoverningBody)) {
            return false;
        }
        NationalGoverningBody ngb = (NationalGoverningBody) o;
        return new EqualsBuilder()
                .append(name, ngb.name)
                .append(abbreviation, ngb.abbreviation)
                .append(location, ngb.location)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(abbreviation)
                .append(location)
                .toHashCode();
    }
}
