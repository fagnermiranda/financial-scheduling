package br.com.fagner.enums;

public enum TransferSchedulingType implements TransferSchedulingTypeStrategy{
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
				return (valueTransfer * 0.08);

			} else if (days > 20 && days <= 30) {
				return (valueTransfer * 0.06);

			} else if (days > 30 && days <= 40) {
				return (valueTransfer * 0.04);

			} else if (days > 40 && (valueTransfer > 100.00)) {
				return (valueTransfer * 0.02);
			}

			return 0;
		}
	};	
}
