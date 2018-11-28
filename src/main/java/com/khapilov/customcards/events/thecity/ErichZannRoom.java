package com.khapilov.customcards.events.thecity;

import com.khapilov.customcards.relics.ErichZannNotes;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;

/**
 * @author Ross Khapilov
 * @version 1.0 24.11.2018
 */
public class ErichZannRoom extends AbstractImageEvent {

    public static final String ID = "Erich Zann's Room";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String DIALOG_1 = DESCRIPTIONS[0];
    private static final String DIALOG_2 = DESCRIPTIONS[1];
    private CurScreen screen;

    public ErichZannRoom() {
        super(NAME, DIALOG_1, "images/events/erichZannRoom.png");
        screen = CurScreen.INTRO;
        imageEventText.setDialogOption(OPTIONS[0] + 30 + OPTIONS[1]);
        imageEventText.setDialogOption(OPTIONS[2]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (screen) {
            case INTRO:
                switch (buttonPressed) {
                    case 0:
                        imageEventText.updateBodyText(DIALOG_2);
                        ErichZannNotes relic = new ErichZannNotes();
                        int hpLoss = (int) ((float) AbstractDungeon.player.maxHealth * 0.7F);
                        int diff = AbstractDungeon.player.maxHealth - hpLoss;
                        AbstractDungeon.player.maxHealth = hpLoss;
                        if (AbstractDungeon.player.maxHealth <= 0)
                            AbstractDungeon.player.maxHealth = 1;
                        if (AbstractDungeon.player.currentHealth > AbstractDungeon.player.maxHealth)
                            AbstractDungeon.player.currentHealth = AbstractDungeon.player.maxHealth;
                        logMetricObtainRelicAndLoseMaxHP("Erich Zann's Room", "Erich Zann's Notes", relic, diff);
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2), relic);
                        CardCrawlGame.sound.play("EVENT_TOME");
                        imageEventText.clearAllDialogs();
                        imageEventText.setDialogOption(OPTIONS[3]);
                        CardCrawlGame.sound.play("VO_AWAKENEDONE_3");
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

    private enum CurScreen {
        INTRO,
        RESULT
    }
}
