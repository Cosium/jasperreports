package net.sf.jasperreports;

/**
 * @author Réda Housni Alaoui
 */
public class SimpleTemplateRow {
	private final String amountLabel;
	private final String amountValue;
	private final String currencySymbol;

	SimpleTemplateRow(String amountLabel, String amountValue, String currencySymbol) {
		this.amountLabel = amountLabel;
		this.amountValue = amountValue;
		this.currencySymbol = currencySymbol;
	}

	public String getAmountLabel() {
		return amountLabel;
	}

	public String getAmountValue() {
		return amountValue;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}
}
