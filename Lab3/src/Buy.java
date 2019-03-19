public class Buy
{

    private String SESSION_ID;
    private String PRODUCT_ID;
    private int PRICE;
    private int QUANTITY;

    public Buy(String session_id, String product_id, int price, int quantity)
    {
        this.SESSION_ID = session_id;
        this.PRODUCT_ID = product_id;
        this.PRICE = price;
        this.QUANTITY = quantity;
    }


    public String getSESSION_ID() {
        return SESSION_ID;
    }

    public String getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    public int getPRICE() {
        return PRICE;
    }

    public int getQUANTITY() {
        return QUANTITY;
    }

}