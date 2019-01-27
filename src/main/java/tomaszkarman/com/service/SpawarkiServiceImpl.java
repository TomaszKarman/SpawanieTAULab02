package tomaszkarman.com.service;

import tomaszkarman.com.domain.SpawarkaCTR;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpawarkiServiceImpl {

    public List<SpawarkaCTR> listaSpawarek = new ArrayList<>();

    public void addSpawarka(SpawarkaCTR spawarka) {
        listaSpawarek.add(spawarka);
        spawarka.setCreateTime(new Date());
    }

    public SpawarkaCTR addSpawarkaDate(SpawarkaCTR spawarka) {
        spawarka.setCreateTime(new Date());
        listaSpawarek.add(spawarka);
        return spawarka;
    }

    public void removeSpawarka(SpawarkaCTR spawarka) {
        listaSpawarek.remove(spawarka);
    }

    public void renameSpawarka(int id, String name) {
        for (SpawarkaCTR spawarka : listaSpawarek) {
            if (spawarka.kod == id) {
                spawarka.name = name;
                spawarka.setModifyTime(new Date());
            }
        }
    }

    public SpawarkaCTR getByID(int id) {
        try {
            for (SpawarkaCTR s : listaSpawarek) {
                if (s.kod == id) {
                    s.setReadTime(this.setCurrentDate());
                    return s;
                }
            }
        } catch (IllegalArgumentException e) {
            //System.out.println("Exception thrown  :" + e);
        }
        //.out.println("Not found");
        throw new IllegalArgumentException("Not found");
    }

    public Date setCurrentDate() {
        return new Date();
    }

    public List<SpawarkaCTR> getListaSpawarek() {
        return listaSpawarek;
    }

    public Date dateConverterFromString(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        return sdf.parse(dateString);
    }

    public SpawarkaCTR spawarkaCTRWithReadDate(String nameOfSpawarka) {
        SpawarkaCTR spawarkaCTRToReturn = null;
        for (SpawarkaCTR spawarkaCTR : listaSpawarek) {
            if (spawarkaCTR.getName().equals(nameOfSpawarka)) {
                spawarkaCTR.setReadTime(new Date());
                spawarkaCTRToReturn = spawarkaCTR;
            }
        }
        return spawarkaCTRToReturn;
    }

    public SpawarkaCTR updateNameOfObject(String currentName, String newName) {
        SpawarkaCTR spawarkaCTRToReturn = null;
        for (SpawarkaCTR spawarkaCTR : listaSpawarek) {
            if (spawarkaCTR.getName().equals(currentName)) {

                spawarkaCTR.setName(newName);
                spawarkaCTR.setModifyTime(new Date());
                spawarkaCTRToReturn = spawarkaCTR;
            }
        }
        return spawarkaCTRToReturn;
    }

    public SpawarkaCTR multiDateUpdater(SpawarkaCTR spawarkaCTR, boolean createDate, Date createDateInfo, boolean readDate, Date readDateInfo, boolean modifyDate, Date modifyDateInfo) {

        if (createDate)
            spawarkaCTR.setCreateTime(createDateInfo);

        if (readDate)
            spawarkaCTR.setReadTime(readDateInfo);

        if (modifyDate)
            spawarkaCTR.setModifyTime(modifyDateInfo);

        return spawarkaCTR;
    }
}