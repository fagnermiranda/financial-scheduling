package br.com.fagner.enums;

public enum TransferSchedulingType {
	A {
		@Override
		public double calculateRate(int days, double valueTransfer) {
			return (3 + (valueTransfer * 0.03));
		}
	}, 
	B {
		@Override
		public double calculateRate(int days, double valueTransfer) {
			return (12 * days);
		}
	}, 
	C {
		@Override
		public double calculateRate(int days, double valueTransfer) {
			if (days > 10 && days <= 20) {
				return (valueTransfer * 8) / 100;

			} else if (days > 20 && days <= 30) {
				return (valueTransfer * 6) / 100;

			} else if (days > 30 && days <= 40) {
				return (valueTransfer * 4) / 100;

			} else if (days > 40 && (valueTransfer > 100.00)) {
				return (valueTransfer * 2) / 100;
			}

			return 0;
		}
	};
	
	public abstract double calculateRate(int days, double valueTransfer);
	
}
