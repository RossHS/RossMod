package com.khapilov.customcards;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.khapilov.customcards.cards.blue.*;
import com.khapilov.customcards.cards.colorless.*;
import com.khapilov.customcards.cards.green.*;
import com.khapilov.customcards.cards.red.*;
import com.khapilov.customcards.events.thecity.ErichZannRoom;
import com.khapilov.customcards.helpers.IncreaseStatsMarker;
import com.khapilov.customcards.helpers.RiseKeyword;
import com.khapilov.customcards.relics.ErichZannNotes;
import com.khapilov.customcards.relics.LivelyStain;
import com.khapilov.customcards.relics.SpikedShield;
import com.khapilov.customcards.relics.TypeCasting;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @author Ross khapilov
 * @version 1.0 10.10.2018
 */
@SpireInitializer
public class RossMod implements PostExhaustSubscriber,
        PostBattleSubscriber,
        PostDungeonInitializeSubscriber,
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        PostInitializeSubscriber,
        EditKeywordsSubscriber,
        StartGameSubscriber,
        PostDrawSubscriber {

    private int count, totalCount;
    public static final Logger LOGGER = LogManager.getLogger(RossMod.class.getName());
    public static KeywordStrings keywordStrings;
    public static CardGroup rorschachCardPool;
    public static CardGroup bluePowerCardPool;

    private void resetCounts() {
        totalCount = count = 0;
    }

    public RossMod() {
        BaseMod.subscribe(this);
        resetCounts();
    }

    public static void initialize() {
        new RossMod();
    }

    @Override
    public void receiveEditRelics() {
        LOGGER.info("adding custom Relics...");
        BaseMod.addRelic(new SpikedShield(), RelicType.RED);
        BaseMod.addRelic(new LivelyStain(), RelicType.GREEN);
        BaseMod.addRelic(new TypeCasting(), RelicType.BLUE);
        BaseMod.addRelic(new ErichZannNotes(), RelicType.SHARED);
    }

    @Override
    public void receiveEditCards() {
        LOGGER.info("adding cards for Ironclad...");
        addCardAndUnlock(new Bolster());
        addCardAndUnlock(new TankUp());
        addCardAndUnlock(new RecklessFlurry());
        addCardAndUnlock(new InSearchOfArmor());
        addCardAndUnlock(new MagicArmor());
        addCardAndUnlock(new GreedyBlock());
        LOGGER.info("adding cards for Silent...");
        addCardAndUnlock(new Doomsday());
        addCardAndUnlock(new RorschachMask());
        addCardAndUnlock(new FearfulSymmetry());
        addCardAndUnlock(new RorschachsJournal());
        addCardAndUnlock(new EyeOpener());
        addCardAndUnlock(new GrowingStain());
        LOGGER.info("adding cards for Defect...");
        addCardAndUnlock(new XAttack());
        addCardAndUnlock(new XBlock());
        addCardAndUnlock(new Charging());
        addCardAndUnlock(new StayFocused());
        addCardAndUnlock(new Supercharger());
        addCardAndUnlock(new DreamWorld());
        LOGGER.info("adding colorless cards...");
        addCardAndUnlock(new BagOfGold(0));
        addCardAndUnlock(new RorschachStainBlock());
        addCardAndUnlock(new RorschachStainCard());
        addCardAndUnlock(new RorschachStainDamage());
        addCardAndUnlock(new RorschachStainEnergy());
        addCardAndUnlock(new RorschachStainHeal());
        addCardAndUnlock(new RorschachStainPoison());
        addCardAndUnlock(new RorschachStainVulnerable());
        addCardAndUnlock(new RorschachStainWeak());
        addCardAndUnlock(new RorschachStainPure());
    }

    private void addCardAndUnlock(AbstractCard card) {
        BaseMod.addCard(card);
        UnlockTracker.unlockCard(card.cardID);
    }

    @Override
    public void receiveEditStrings() {
        LOGGER.info("begin editing strings");
        String jsonPath = "localization/eng/";

        if (Settings.language.toString().equals("RUS")) {
            LOGGER.info("Russian language detected");
            jsonPath = "localization/rus/";
        }

        editStringsByLang(jsonPath);
        LOGGER.info("done editing strings");
    }


    private void editStringsByLang(String jsonPath) {
        // CardStrings
        String cardStrings = Gdx.files.internal(jsonPath + "CustomCardStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        // PowerStrings
        String powerStrings = Gdx.files.internal(jsonPath + "CustomPowerStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
        // PowerStrings
        String relicStrings = Gdx.files.internal(jsonPath + "CustomRelicStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        // EventStrings
        String eventStrings = Gdx.files.internal(jsonPath + "CustomEventStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(EventStrings.class, eventStrings);
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        System.out.println(count + " cards were exhausted this battle, " +
                totalCount + " cards have been exhausted so far this act.");
        count = 0;
    }

    @Override
    public void receivePostDungeonInitialize() {
        resetCounts();
    }

    @Override
    public void receivePostExhaust(AbstractCard abstractCard) {
        count++;
        totalCount++;
    }

    @Override
    public void receivePostInitialize() {
        rorschachCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
        buildRorschachCardPool();

        bluePowerCardPool = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
        ArrayList<AbstractCard> tmpPool = new ArrayList<>();
        CardLibrary.addBlueCards(tmpPool);
        for (AbstractCard card : tmpPool) {
            if (card.type == AbstractCard.CardType.POWER) bluePowerCardPool.addToTop(card);
        }
        keywordStrings = com.megacrit.cardcrawl.core.CardCrawlGame.languagePack.getKeywordString("Game Dictionary");

        LOGGER.info("Load custom events...");

        BaseMod.addEvent(ErichZannRoom.ID, ErichZannRoom.class,"TheCity");
    }

    private void buildRorschachCardPool() {
        rorschachCardPool.addToTop(new RorschachStainBlock());
        rorschachCardPool.addToTop(new RorschachStainCard());
        rorschachCardPool.addToTop(new RorschachStainDamage());
        rorschachCardPool.addToTop(new RorschachStainEnergy());
        rorschachCardPool.addToTop(new RorschachStainHeal());
        rorschachCardPool.addToTop(new RorschachStainPoison());
        rorschachCardPool.addToTop(new RorschachStainVulnerable());
        rorschachCardPool.addToTop(new RorschachStainWeak());
        rorschachCardPool.addToTop(new RorschachStainPure());
    }

    @Override
    public void receiveEditKeywords() {
        LOGGER.info("begin editing keywords");
        String jsonPath = "localization/eng/";

        if (Settings.language.toString().equals("RUS")) {
            LOGGER.info("Russian language detected");
            jsonPath = "localization/rus/";
        }

        editKeywordsByLang(jsonPath);
        LOGGER.info("done editing keywords");
    }

    private void editKeywordsByLang(String jsonPath) {
        Gson gson = new Gson();
        String keywordStrings = Gdx.files.internal(jsonPath + "CustomKeywordStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(keywordStrings, Keyword[].class);
        if (keywords != null)
            for (Keyword k : keywords) BaseMod.addKeyword(k.NAMES, k.DESCRIPTION);
    }


    @Override
    public void receiveStartGame() {
        for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
            if (card instanceof IncreaseStatsMarker) card.applyPowers();
        }
    }

    //TODO улучшить алгоритм, чтобы каждый раз не пробегать по коллекции, а лишь когда такие карты есть в колоде

    private static int turn = 0;

    @Override
    public void receivePostDraw(AbstractCard abstractCard) {
        if (turn != GameActionManager.turn) {
            turn = GameActionManager.turn;
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c instanceof RiseKeyword) ((RiseKeyword) c).rise();
            }
            for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                if (c instanceof RiseKeyword) ((RiseKeyword) c).rise();
            }
            for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                if (c instanceof RiseKeyword) ((RiseKeyword) c).rise();
            }
        }
    }
}
