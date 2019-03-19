public class View
{

    private String SESSION_ID;
    private String PRODUCT_ID;
    private int PRICE;

    public View(String session_id, String product_id, int price)
    {
        this.SESSION_ID = session_id;
        this.PRODUCT_ID = product_id;
        this.PRICE = price;
    }

    public String getSESSION_ID()
    {
        return SESSION_ID;
    }


    public String getPRODUCT_ID()
    {
        return PRODUCT_ID;
    }

    public String getProduct()
    {
        return PRODUCT_ID;
    }

    public int getPRICE()
    {
        return PRICE;
    }



}