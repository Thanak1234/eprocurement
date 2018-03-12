package com.eprocurement.department;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//TODO add auditing
@Entity
public class Department {

		@Id
		@GeneratedValue
		private Long id;
		
		private String departmentName;
		
		private String departmentHead;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDepartmentName() {
			return departmentName;
		}

		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}

		public String getDepartmentHead() {
			return departmentHead;
		}

		public void setDepartmentHead(String departmentHead) {
			this.departmentHead = departmentHead;
		}
		
		
}
