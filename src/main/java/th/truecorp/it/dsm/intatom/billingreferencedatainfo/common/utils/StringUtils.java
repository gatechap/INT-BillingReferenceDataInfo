package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils;

public class StringUtils {

    public static String checkNull(String val)
    {
        return stringDef(val, "");
    }

    public static String stringDef(String val, String def)
    {
        String s = "";
        try {
            if (val != null)
            {
                s = val.trim();
            }
            else
            {
                if (def != null)
                {
                    s = def.trim();
                }
                else
                {
                    s = "";
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return  s;
    }

    public static String convertStrToStrTokenize(String[] val){
        String result = "";
        if(val!=null && val.length>0){
            for(int i=0;i<val.length;i++){
                if(i == 0){
                    result += val[i];
                }else{
                    result += ","+val[i];
                }
            }
        }
        return result;
    }
}
