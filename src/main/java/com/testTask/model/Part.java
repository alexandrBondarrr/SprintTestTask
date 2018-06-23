package com.testTask.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "part")
@NamedQueries({
        @NamedQuery(
          name="listAll",
          query="from Part where isRequired IN :isRequired AND name LIKE :query"
        ),
        @NamedQuery(
          name="getOneById",
          query="from Part where id = :id"
        )
})
public class Part {

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name", unique = true)
   @Size(min = 3, max = 100, message = "{part.name.invalid}")
   @NotEmpty(message="Please Enter Part Name")
   private String name;

   @Column(name = "isRequired")
   private boolean isRequired;

   @Column(name = "count")
   @NotNull(message = "{part.count.invalid}")
   @Min(value=0, message = "{part.count.invalid}")
   @Max(value=1000000000, message = "{part.count.invalid}")
   private Long count;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public boolean isRequired() {
      return isRequired;
   }

   public boolean getIsRequired() {
      return isRequired;
   }

   public void setIsRequired(boolean required) {
      isRequired = required;
   }

   public Long getCount() {
      return count;
   }

   public void setCount(Long count) {
      this.count = count;
   }
}
