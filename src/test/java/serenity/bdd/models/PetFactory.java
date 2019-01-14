package serenity.bdd.models;

import com.google.common.collect.ImmutableList;
import serenity.bdd.statuses.PetStatus;

/**
 * Created by : Volodymyr_Silitskyi
 * Created at : 12/6/2018
 */


public class PetFactory {

    private void PetFactory() {
    }

    public static Pet createBarsik() {
        Category category = new Category();
        category.setName("Cats");
        category.setId(123123);

        Pet cat = new Pet();
        cat.setId(807011);
        cat.setName("BarsikSV5");
        cat.setCategory(category);
        cat.setPhotoUrls(ImmutableList.of("someUrl"));
        cat.setStatus(PetStatus.AVAILABLE);
        return cat;
    }
}
