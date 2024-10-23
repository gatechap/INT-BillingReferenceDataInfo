package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.uuid;

public class UUIDGeneratorSingleton {
    private GUIDGenerator guidGenerator = null;
    private static UUIDGeneratorSingleton instance = null;


    public static UUIDGeneratorSingleton getInstance() throws GUIDException {
        if ( instance == null ) {
            synchronized (UUIDGeneratorSingleton.class) {
                instance = new UUIDGeneratorSingleton();
            }
        }
        return instance;
    }

    public UUIDGeneratorSingleton() throws GUIDException {
        guidGenerator = new GUIDGenerator();
    }

    public String getUUID() {
        return guidGenerator.getUnformatedUUID();
    }


}

