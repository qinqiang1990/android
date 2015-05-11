package com.example.sms;

import java.io.Serializable;

public class MailMessage  implements Serializable {
		public String getFrom() {
			return from;
		}
		public void setFrom(String from) {
			this.from = from;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		private String id;
		private String from;
		private String subject;
	}