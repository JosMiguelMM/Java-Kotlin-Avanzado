package model;

import java.util.Date;

public interface IVisualizable {

  /**
   * Este método captura el tiempo exacto de inicio de visualización
   * @param dateI Es un objeto {@code Date} con el tiempo de inicio exato
   * @return  Devuelve la fecha y hora capturada
   */
  Date startToSee(Date dateI);

  /**
   * Este método captura el tiempo exacto de inicio y final de visualización
   *
   * @param dateI dateI es un objeto de tipo Date
   * @param dateF
   */
  void stopToSee(Date dateI, Date dateF);
}
