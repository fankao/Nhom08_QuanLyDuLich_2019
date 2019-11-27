package entities;

public enum LoaiPhieu {
	PHIEUTHU {
		@Override
		public String toString() {

			return "Phiếu thu";
		}

	},
	PHIEUCHI {
		@Override
		public String toString() {
			return "Phiếu chi";
		}
	}
}
