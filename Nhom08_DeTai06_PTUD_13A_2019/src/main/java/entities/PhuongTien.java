package entities;

public enum PhuongTien {
	XE {
		@Override
		public String toString() {
			return "Đi bằng xe";
		}
	},
	HANGKHONG {
		;
		@Override
		public String toString() {
			return "Hàng không";
		}
	}
}
