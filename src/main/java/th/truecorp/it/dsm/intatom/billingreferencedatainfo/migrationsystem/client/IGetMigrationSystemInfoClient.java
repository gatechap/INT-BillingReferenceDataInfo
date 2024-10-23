package th.truecorp.it.dsm.intatom.billingreferencedatainfo.migrationsystem.client;

import th.truecorp.it.dsm.intatom.billingreferencedatainfo.migrationsystem.model.RequestGetMigrationSystemInfo;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.migrationsystem.model.ResponseGetMigrationSystemInfo;

public interface IGetMigrationSystemInfoClient {


	ResponseGetMigrationSystemInfo getMigrationSystemInfo(RequestGetMigrationSystemInfo request, String correlatedId,
														  String controllerPath, String uuid) throws Exception;

}
