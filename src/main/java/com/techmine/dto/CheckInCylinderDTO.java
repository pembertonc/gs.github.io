/*
 * Copyright 2021 CodeCamp4.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.techmine.dto;

import com.techmine.domain.Location;
import com.techmine.domain.Person;
import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author CodeCamp4
 */
public class CheckInCylinderDTO implements Serializable{
    
   private String SerialNo;
   private LocalDate dateReturned;
   @NotBlank
   private String customerRepName;
   private Person employee;
   private Location receivedAt;

    public CheckInCylinderDTO() {
        employee = new Person();
        receivedAt = new Location();
        
    }

    public String getSerialNo() {
        return SerialNo;
    }

    public void setSerialNo(String SerialNo) {
        this.SerialNo = SerialNo;
    }

    public LocalDate getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }

    public String getCustomerRepName() {
        return customerRepName;
    }

    public void setCustomerRepName(String customerRepName) {
        this.customerRepName = customerRepName;
    }

    public Person getEmployee() {
        return employee;
    }

    public void setEmploye(Person employee) {
        this.employee = employee;
    }

    public Location getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(Location receivedAt) {
        this.receivedAt = receivedAt;
    }
   
    
}
