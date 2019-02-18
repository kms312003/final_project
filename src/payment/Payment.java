package payment;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Payment {

	private String imp_uid; 		// 거래 고유 번호
	private String merchant_uid;	// 주문번호
	private String pay_method;		// 결제수단
	private int card_quota;			// 카드할부
	private String vbank_name;		// 가상계좌 은행명
	private String vbank_num;		// 가상계좌 입금계좌번호
	private String vbank_holder;	// 가상계좌 예금주
	private long vbank_date;		// 가상계좌 입금기한
	private String name;			// 주문명
	private BigDecimal amount;		// 결제 금액
	private String status;
	private long paid_at;			// 결제승인시각

	public String getImpUid() {
		return imp_uid;
	}

	public String getMerchantUid() {
		return merchant_uid;
	}

	public String getPayMethod() {
		return pay_method;
	}
	
	public int getCardQuota() {
		return card_quota;
	}

	public String getVbankName() {
		return vbank_name;
	}

	public String getVbankNum() {
		return vbank_num;
	}

	public String getVbankHolder() {
		return vbank_holder;
	}

	public Date getVbankDate() {
		return new Date( vbank_date * 1000L );
	}

	public String getName() {
		return name;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getStatus() {
		return status;
	}

	public Date getPaidAt() {
		return new Date( paid_at * 1000L );
	}
}
