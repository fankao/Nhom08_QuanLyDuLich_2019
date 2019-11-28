package entities;

public enum DoTuoi {
	NGUOILON {
		@Override
		public String toString() {
			return "Người lớn";
		}
	},
	TREEM {
		@Override
		public String toString() {
			return "Trẻ em";
		}
	}
}
