package kr.co.fastcampus.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue
    @Setter
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;


    @Transient//디비에 처리하지 않음
    @JsonInclude(JsonInclude.Include.NON_NULL)  //널이 아닐때만 json에 넣어줘라
    private List<MenuItem> menuItems;

    public String getInformation() {
        return  name + " in " + address;
    }



    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>(menuItems);
    }

    public void updateInformamtion(String name, String address) {
        this.name = name;
        this.address = address;
    }
}