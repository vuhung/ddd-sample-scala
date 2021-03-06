package se.citerus.dddsample.domain.model.voyage;

import se.citerus.dddsample.application.util.DateTestUtil
import se.citerus.dddsample.domain.model.location.Location;
import se.citerus.dddsample.domain.model.location.SampleLocations._;

import java.util.Date

/**
 * Sample carrier movements, for test purposes.
 *
 */
object SampleVoyages extends DateTestUtil {

  val CM001 = createVoyage("CM001", STOCKHOLM, HAMBURG);
  val CM002 = createVoyage("CM002", HAMBURG, HONGKONG);
  val CM003 = createVoyage("CM003", HONGKONG, NEWYORK);
  val CM004 = createVoyage("CM004", NEWYORK, CHICAGO);
  val CM005 = createVoyage("CM005", CHICAGO, HAMBURG);
  val CM006 = createVoyage("CM006", HAMBURG, HANGZHOU);
  
  private def createVoyage(id:String, from:Location, to:Location) : Voyage = {
    new Voyage(new VoyageNumber(id), new Schedule(List(new CarrierMovement(from, to, new Date(), new Date()))))
  }

  // TODO CM00[1-6] and createVoyage are deprecated. Remove and refactor tests.

  val v100 = new VoyageBuilder(new VoyageNumber("V100"), HONGKONG).
    addMovement(TOKYO, toDate("2009-03-03"), toDate("2009-03-05")).
    addMovement(NEWYORK, toDate("2009-03-06"), toDate("2009-03-09")).
    build();
  val v200 = new VoyageBuilder(new VoyageNumber("V200"), TOKYO).
      addMovement(NEWYORK, toDate("2009-03-06"), toDate("2009-03-08")).
      addMovement(CHICAGO, toDate("2009-03-10"), toDate("2009-03-14")).
      addMovement(STOCKHOLM, toDate("2009-03-14"), toDate("2009-03-16")).
      build();
  val v300 = new VoyageBuilder(new VoyageNumber("V300"), TOKYO).
        addMovement(ROTTERDAM, toDate("2009-03-08"), toDate("2009-03-11")).
        addMovement(HAMBURG, toDate("2009-03-11"), toDate("2009-03-12")).
        addMovement(MELBOURNE, toDate("2009-03-14"), toDate("2009-03-18")).
        addMovement(TOKYO, toDate("2009-03-19"), toDate("2009-03-21")).
        build();
  val v400 = new VoyageBuilder(new VoyageNumber("V400"), HAMBURG).
          addMovement(STOCKHOLM, toDate("2009-03-14"), toDate("2009-03-15")).
          addMovement(HELSINKI, toDate("2009-03-15"), toDate("2009-03-16")).
          addMovement(HAMBURG, toDate("2009-03-20"), toDate("2009-03-22")).
          build();
  
  /**
   * Voyage number 0100S (by ship)
   *
   * Hongkong - Hangzou - Tokyo - Melbourne - New York
   */
  val HONGKONG_TO_NEW_YORK =
    new VoyageBuilder(new VoyageNumber("0100S"), HONGKONG).
      addMovement(HANGZHOU, toDate("2008-10-01", "12:00"), toDate("2008-10-03", "14:30")).
      addMovement(TOKYO, toDate("2008-10-03", "21:00"), toDate("2008-10-06", "06:15")).
      addMovement(MELBOURNE, toDate("2008-10-06", "11:00"), toDate("2008-10-12", "11:30")).
      addMovement(NEWYORK, toDate("2008-10-14", "12:00"), toDate("2008-10-23", "23:10")).
      build();

  /**
   * Voyage number 0200T (by train)
   *
   * New York - Chicago - Dallas
   */
  val NEW_YORK_TO_DALLAS =
    new VoyageBuilder(new VoyageNumber("0200T"), NEWYORK).
      addMovement(CHICAGO, toDate("2008-10-24", "07:00"), toDate("2008-10-24", "17:45")).
      addMovement(DALLAS, toDate("2008-10-24", "21:25"), toDate("2008-10-25", "19:30")).
      build();
  
  /**
   * Voyage number 0300A (by airplane)
   *
   * Dallas - Hamburg - Stockholm - Helsinki
   */
  val DALLAS_TO_HELSINKI =
    new VoyageBuilder(new VoyageNumber("0300A"), DALLAS).
      addMovement(HAMBURG, toDate("2008-10-29", "03:30"), toDate("2008-10-31", "14:00")).
      addMovement(STOCKHOLM, toDate("2008-11-01", "15:20"), toDate("2008-11-01", "18:40")).
      addMovement(HELSINKI, toDate("2008-11-02", "09:00"), toDate("2008-11-02", "11:15")).
      build();

  /**
   * Voyage number 0301S (by ship)
   *
   * Dallas - Hamburg - Stockholm - Helsinki, alternate route
   */
  val DALLAS_TO_HELSINKI_ALT =
    new VoyageBuilder(new VoyageNumber("0301S"), DALLAS).
      addMovement(HELSINKI, toDate("2008-10-29", "03:30"), toDate("2008-11-05", "15:45")).
      build();

  /**
   * Voyage number 0400S (by ship)
   *
   * Helsinki - Rotterdam - Shanghai - Hongkong
   *
   */
  val HELSINKI_TO_HONGKONG =
    new VoyageBuilder(new VoyageNumber("0400S"), HELSINKI).
      addMovement(ROTTERDAM, toDate("2008-11-04", "05:50"), toDate("2008-11-06", "14:10")).
      addMovement(SHANGHAI, toDate("2008-11-10", "21:45"), toDate("2008-11-22", "16:40")).
      addMovement(HONGKONG, toDate("2008-11-24", "07:00"), toDate("2008-11-28", "13:37")).
      build();

  private val voyageList : List[Voyage] = List(v100, v200, v300, v400, 
      HONGKONG_TO_NEW_YORK, NEW_YORK_TO_DALLAS, DALLAS_TO_HELSINKI, DALLAS_TO_HELSINKI_ALT, HELSINKI_TO_HONGKONG)

  private val voyageMap: Map[VoyageNumber, Voyage] = Map() ++ voyageList.map{ v => (v.voyageNumber, v) } 

  def getAll() : List[Voyage] = {
    voyageList;
  }

  def lookup(voyageNumber:VoyageNumber) : Option[Voyage] = {
    voyageMap.get(voyageNumber);
  }
}
