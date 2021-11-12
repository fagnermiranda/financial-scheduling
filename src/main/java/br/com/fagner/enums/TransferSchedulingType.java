package br.com.fagner.enums;

import br.com.fagner.constants.Percentage;

public enum TransferSchedulingType implements TransferSchedulingTypeStrategy{
	A {
		@Override
		public double calculateRate(long days, double valueTransfer) {
			return (3 + (valueTransfer * Percentage.THREE));
		}
	}, 
	B {
		@Override
		public double calculateRate(long days, double valueTransfer) {
			return (12 * days);
		}
	}, 
	C {
		@Override
		public double calculateRate(long days, double valueTransfer) {
			if (isEightPercent(days)) {
				return (valueTransfer * Percentage.EIGHT);
			}
			if (isSixPercent(days)) {
				return (valueTransfer * Percentage.SIX);
			} 
			if (isFourPercent(days)) {
				return (valueTransfer * Percentage.FOUR);
			} 
			if (isTwoPercent(days, valueTransfer)) {
				return (valueTransfer * Percentage.TWO);
			}
			return 0;
		}

		private boolean isTwoPercent(long days, double valueTransfer) {
			return days > 40 && (valueTransfer > 100000);
		}

		private boolean isFourPercent(long days) {
			return days > 30 && days <= 40;
		}

		private boolean isSixPercent(long days) {
			return days > 20 && days <= 30;
		}

		private boolean isEightPercent(long days) {
			return days > 10 && days <= 20;
		}
	};	
}
