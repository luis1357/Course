package com.yeah.ruisu.week2day3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class FillSql {

    private Context MyContext;
    private DatabaseHelper MyDataHelper;

    public FillSql(Context InContext)
    {
        MyContext = InContext;

        /* We create the database helper. */
        MyDataHelper = new DatabaseHelper(MyContext);

    }

    public void CreateAnimals()
    {
        CreateAmphibians();
        CreateBirds();
        CreateInvertebrates();
        CreateMammals();
        CreateReptiles();
    }

    public void CreateAmphibians ()
    {
        MyDataHelper.InsertData("amphibians",
                "gopher_frog",
                "gopher_frog_dsc",
                "frog",
                "frog_snd");

        MyDataHelper.InsertData("amphibians",
                "japanese_salamander",
                "japanese_salamander_dsc",
                "salamander",
                "salamander_snd");

        MyDataHelper.InsertData("amphibians",
                "mantella_frog",
                "mantella_frog_dsc",
                "frogm",
                "frog_snd");
    }

    public void CreateBirds ()
    {
        MyDataHelper.InsertData("birds",
                "african_pygmy_goose",
                "african_pygmy_goose_dsc",
                "goose",
                "goose_snd");

        MyDataHelper.InsertData("birds",
                "bald_eagle",
                "bald_eagle_dsc",
                "eagle",
                "eagle_snd");

        MyDataHelper.InsertData("birds",
                "gentoo_penguin",
                "gentoo_penguin_dsc",
                "penguin",
                "penguin_snd");
    }

    public void CreateInvertebrates ()
    {
        MyDataHelper.InsertData("invertebrates",
                "butterfly",
                "butterfly_dsc",
                "butterfly",
                "butterfly_snd");

    }

    public void CreateMammals ()
    {
        MyDataHelper.InsertData("mammals",
                "arctic_fox",
                "arctic_fox_dsc",
                "fox",
                "fox_snd");

        MyDataHelper.InsertData("mammals",
                "american_beaver",
                "american_beaver_dsc",
                "beaver",
                "beaver_snd");

        MyDataHelper.InsertData("mammals",
                "miniature_donkey",
                "miniature_donkey_dsc",
                "donkey",
                "donkey_snd");

        MyDataHelper.InsertData("mammals",
                "horse",
                "horse_dsc",
                "horse",
                "horse_snd");


        MyDataHelper.InsertData("mammals",
                "otter",
                "otter_dsc",
                "otter",
                "otter_snd");

    }

    public void CreateReptiles ()
    {
        MyDataHelper.InsertData("reptiles",
                "african_spurred_tortoise",
                "african_spurred_tortoise_dsc",
                "tortoise",
                "tortoise_snd");

        MyDataHelper.InsertData("reptiles",
                "chinese_alligator",
                "chinese_alligator_dsc",
                "alligator",
                "alligator_snd");

        MyDataHelper.InsertData("reptiles",
                "rattlesnake",
                "rattlesnake_dsc",
                "snake",
                "rattlesnake_snd");
    }
}
