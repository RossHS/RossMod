package com.khapilov.customcards.cards.green;

import basemod.abstracts.CustomCard;
import com.khapilov.customcards.RossMod;
import com.khapilov.customcards.actions.MakeCardGroupInDrawPileAction;
import com.khapilov.customcards.helpers.IncreaseStatsMarker;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * @author Ross Khapilov
 * @version 1.0 31.10.2018
 */
public class GrowingStain extends CustomCard implements IncreaseStatsMarker {
    public static final String ID = "Growing Stain";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 1;
    private int stack = 1;


    public GrowingStain() {
        super(ID, NAME, "cards/growingStain.png", COST, DESCRIPTION, CardType.SKILL, CardColor.GREEN, CardRarity.UNCOMMON, CardTarget.SELF);
        misc = 1;
        magicNumber = baseMagicNumber = misc;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new IncreaseMiscAction(uuid, misc, stack));
        AbstractDungeon.actionManager.addToBottom(new MakeCardGroupInDrawPileAction(baseMagicNumber, RossMod.rorschachCardPool, true, true));
    }

    public void applyPowers() {
//        RossMod.LOGGER.info(ID + "    MISC!!!  " + misc);
        baseMagicNumber = misc;
        super.applyPowers();
        initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new GrowingStain();
    }

    public void upgrade() {
        if (!this.upgraded) {
            stack++;
            this.upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }

    }
}
