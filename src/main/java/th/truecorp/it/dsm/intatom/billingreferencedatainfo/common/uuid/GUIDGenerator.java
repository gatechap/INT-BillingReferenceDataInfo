package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.uuid;

import java.net.InetAddress;
import java.security.SecureRandom;

public class GUIDGenerator {

    /** Creates new GUIDGenerator */
    public GUIDGenerator()
            throws GUIDException
    {

        try
        {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb1 = new StringBuilder();
            seeder = new SecureRandom();
            InetAddress inetaddress = InetAddress.getLocalHost();
            byte abyte0[] = inetaddress.getAddress();
            String s = hexFormat(getInt(abyte0), 8);
            String s1 = hexFormat(hashCode(), 8);
            sb.append("-");
            sb1.append(s.substring(0, 4));
            sb.append(s.substring(0, 4));
            sb.append("-");
            sb1.append(s.substring(4));
            sb.append(s.substring(4));
            sb.append("-");
            sb1.append(s1.substring(0, 4));
            sb.append(s1.substring(0, 4));
            sb.append("-");
            sb1.append(s1.substring(4));
            sb.append(s1.substring(4));
            midValue = sb.toString();
            midValueUnformated = sb1.toString();
//            int i = seeder.nextInt();
        }
        catch(Exception exception)
        {
            throw new GUIDException("error - failure to instantiate GUIDGenerator" +
                    exception);
        }
    }


    /** <p>
     * The private method that actually does the work. The String passed into the
     * method is either the formatted or unformatted mid value which is combined
     * with the low 32 bits (obtained by a bit wise &) of the time and the next value
     * in the secureRandom sequence.
     * </p>
     * @param s The string containing the mid value of the required format for the UUID.
     * @return A string containing the UUID in the desired format.

     */
    private String getVal(String s)
    {
        int i = (int)System.currentTimeMillis() & 0xffffffff;
        int j = seeder.nextInt();
        return hexFormat(i, 8) + s + hexFormat(j, 8);
    }

    public String getUnformatedUUID()
    {
        return getVal(midValueUnformated);
    }

    /** <p>
     * Returns a UUID formated according to the draft internet standard. See
     * the class level documentation for more details.
     * </P>
     *
     * @return A String representing a UUID in the format xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx.
     */
    public String getUUID()
    {
        return getVal(midValue);
    }

    /** <p>
     * A utility method to take a byte array of 4 bytes and produce an int value. This
     * is used to convert the quad xxx.xxx.xxx.xxx value of the IP address to the
     * underlying 32-bit int that the ip address represents. There is no way to
     * obtain this value in Java so we need to convert it ourselves.
     * </P
     * @param abyte0 Th byte array containg 4 bytes that represent an IP address.
     * @return An int that is the actual value of the ip address.
     */
    private int getInt(byte abyte0[])
    {
        int i = 0;
        int j = 24;
        for(int k = 0; j >= 0; k++)
        {
            int l = abyte0[k] & 0xff;
            i += l << j;
            j -= 8;
        }

        return i;
    }

    /** <p>
     * A utility method to produce a correctly formatted hex string string from an int
     * value and and an int specifying the length the hex string that represents the
     * int value should be.
     * </p>
     * <p>
     * Utilises both the padHex and toHexString methods.
     * </p>
     * @param i The int value that is to be transformed to a hex string.
     * @param j An int specifying the length of the hex string to be returned.
     * @return A string that contains the formatted hex string.
     */
    private String hexFormat(int i, int j)
    {
        String s = Integer.toHexString(i);
        return padHex(s, j) + s;
    }

    /** <p>
     * A utility method that takes in a string of hex characters and prepends a number
     * characters to the string to make up a string of the required length as defined
     * in the int value passed into the method. This is because the values for say the
     * hashcode on a lower memory machine will only be 4 characters long and so to
     * the correct formatting is produced 0 characters must be prepended to the fornt
     * of the string.
     * <p>
     * @param s The String containing the hex values.
     * @param i The int specifying the length that the string should be.
     * @return A String of the correct length containing the original hex value and a
     * number of pad zeros at the front of the string.
     */
    private String padHex(String s, int i)
    {
        StringBuffer stringbuffer = new StringBuffer();
        if(s.length() < i)
        {
            for(int j = 0; j < i - s.length(); j++)
                stringbuffer.append("0");

        }
        return stringbuffer.toString();
    }
    /**
     * <p>The random seed used in the method call to provide the required randomized
     * element. The normal random class is not used as the sequences produced are more
     * uniform than this implementation and will produce a predictable sequence which
     * could lead to a greater chance of number clashes.
     * <p>
     */
    private SecureRandom seeder;
    /** <p>
     * The cached mid value of the UUID. This consists of the hexadecimal version of
     * the IP address of the machine and the object's hashcode. These are stored as
     * -xxxx-xxxx-xxxx-xxxx to speed up the method calls. This value does not change
     * over the lifespan of the object and so is able to be cached in this manner.
     * <p>
     */
    private String midValue;
    /** <p>
     * The unformatted cached mid value of the UUID. This consists of the hexadecimal
     * version of the IP address of the machine and the object's hashcode. These are
     * stored as xxxxxxxxxxxxxxxx to speed up the method calls. This value does not change
     * over the lifespan of the object and so is able to be cached in this manner. This
     * vlaue is used to supply the middle part of the UUID for the unformatted method.
     * <p>
     */
    private String midValueUnformated;
}