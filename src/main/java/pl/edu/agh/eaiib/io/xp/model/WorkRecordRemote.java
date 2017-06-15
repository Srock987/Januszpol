package pl.edu.agh.eaiib.io.xp.model;

import java.time.LocalDate;

/**
 * Created by HP on 2017-06-15.
 */
public interface WorkRecordRemote extends DataRecordRemote {

   CompanyRemote getCompany();

   void setCompany(CompanyRemote company);

   int getHours();

   void setHours(int hours);

   LocalDate getDate();

   void setDate(LocalDate date);

   String getCompanyName();

}
