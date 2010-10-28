package com.test.conversion.enums;

public interface FileHeader {
	enum ACCESS_CODE {
		EMPLOYEE_UNIQUE_ID(0), AWARD_AMOUNT(1), ACCESS_CODE_VALUE(2);

		private int index;

		private ACCESS_CODE(int index) {
			this.index = index;
		}

		public int getIndex() {
			return this.index;
		}
	}

	enum POINTS {
		EMPLOYEE_UNIQUE_ID(1), POINTS_BALANCE(7);

		private int index;

		private POINTS(int index) {
			this.index = index;
		}

		public int getIndex() {
			return this.index;
		}
	}
}
