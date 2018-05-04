package com.mgdy.vesta.application.house.DTO;

import java.util.List;

/**
 * Created by 90544 on 2017/7/12.
 */
public class HouseListDTO {
    private List<HouseWebDTO> economicsHouseList;
    private List<HouseWebDTO> comfortableHouseList;
    private List<HouseWebDTO> highgradeHouseList;
    private List<HouseWebDTO> lucuryHouseList;

    public List<HouseWebDTO> getEconomicsHouseList() {
        return economicsHouseList;
    }

    public void setEconomicsHouseList(List<HouseWebDTO> economicsHouseList) {
        this.economicsHouseList = economicsHouseList;
    }

    public List<HouseWebDTO> getComfortableHouseList() {
        return comfortableHouseList;
    }

    public void setComfortableHouseList(List<HouseWebDTO> comfortableHouseList) {
        this.comfortableHouseList = comfortableHouseList;
    }

    public List<HouseWebDTO> getHighgradeHouseList() {
        return highgradeHouseList;
    }

    public void setHighgradeHouseList(List<HouseWebDTO> highgradeHouseList) {
        this.highgradeHouseList = highgradeHouseList;
    }

    public List<HouseWebDTO> getLucuryHouseList() {
        return lucuryHouseList;
    }

    public void setLucuryHouseList(List<HouseWebDTO> lucuryHouseList) {
        this.lucuryHouseList = lucuryHouseList;
    }
}
