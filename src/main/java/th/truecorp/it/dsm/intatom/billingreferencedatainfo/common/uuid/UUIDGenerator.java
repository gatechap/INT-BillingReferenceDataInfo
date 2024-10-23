package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.uuid;

public class UUIDGenerator {

    public String genUUID() throws Exception
    {
        UUIDGeneratorSingleton uuid;
        String result = "";
        try
        {
            uuid = UUIDGeneratorSingleton.getInstance();
            result = uuid.getUUID().substring(0, 8) +"-" +
                    uuid.getUUID().substring(8, 12) +"-" +
                    uuid.getUUID().substring(12, 16) + "-" +
                    uuid.getUUID().substring(16, 20) + "-" +
                    uuid.getUUID().substring(20, 32);

        }
        catch (GUIDException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
        return result;
    }


    public static void main(String args[])
    {
        UUIDGenerator yy = new UUIDGenerator();
        String result = "";
        try
        {
            result = yy.genUUID();
            System.out.println("result=="+result);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}