package com.khapilov.customcards.events.thebeyond;

import com.khapilov.customcards.cards.curses.Nightmare;
import com.khapilov.customcards.relics.CthulhuStatuette;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ross Khapilov
 * @version 1.0 26.11.2018
 */
public class MadnessOutOfTheSea extends AbstractImageEvent {
    public static final String ID = "Madness Out Of The Sea";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String DIALOG_1 = DESCRIPTIONS[0];
    private static final String DIALOG_2 = DESCRIPTIONS[1];
    private CurScreen screen;

    public MadnessOutOfTheSea() {
        super(NAME, DIALOG_1, "images/events/madnessOutOfTheSea.png");
        screen = CurScreen.INTRO;
        imageEventText.setDialogOption(OPTIONS[0], CardLibrary.getCopy(Nightmare.ID));
        imageEventText.setDialogOption(OPTIONS[1]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (screen) {
            case INTRO:
                switch (buttonPressed) {
                    case 0:
                        imageEventText.updateBodyText(DIALOG_2);
                        CthulhuStatuette relic = new CthulhuStatuette();
                        List<String> cardsAdded = new ArrayList<>();
                        cardsAdded.add(Nightmare.ID);
                        cardsAdded.add(Nightmare.ID);
                        cardsAdded.add(Nightmare.ID);
                        List<String> relicAdded = new ArrayList<>();
                        relicAdded.add(CthulhuStatuette.ID);
                        logMetric("Madness Out Of The Sea", "Pick up the statuette", cardsAdded, null, null, null, relicAdded, null, null, 0, 0, 0, 0, 0, 0);
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2), relic);
                        CardCrawlGame.screenShake.rumble(1.5f);
                        CardCrawlGame.sound.play("VO_NEMESIS_1C");
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Nightmare(), (float) Settings.WIDTH * 0.5F, (float) Settings.HEIGHT / 2.0F));
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Nightmare(), (float) Settings.WIDTH * 0.7F, (float) Settings.HEIGHT / 2.0F));
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Nightmare(), (float) Settings.WIDTH * 0.3F, (float) Settings.HEIGHT / 2.0F));
                        imageEventText.clearAllDialogs();
                        imageEventText.setDialogOption(OPTIONS[2]);
                        break;
                    case 1:
                        this.imageEventText.clearRemainingOptions();
                        this.openMap();
                        break;
                }
                screen = CurScreen.RESULT;
                break;
            default:
                openMap();
        }
    }

    @Override
    public void onEnterRoom() {
        if (Settings.AMBIANCE_ON)
            CardCrawlGame.sound.play("EVENT_OOZE");
    }

    private enum CurScreen {
        INTRO,
        RESULT
    }
}
