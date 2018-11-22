package com.khapilov.customcards.cards.colorless;

import basemod.abstracts.CustomCard;
import com.khapilov.customcards.actions.ObtainGoldAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * @author Ross Khapilov
 * @version 1.0 20.10.2018
 */
public class BagOfGold extends CustomCard {
    public static final String ID = "Bag Of Gold";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public static final String DESCRIPTION = EXTENDED_DESCRIPTION[0] + 10 + EXTENDED_DESCRIPTION[1] + 30 + EXTENDED_DESCRIPTION[2];
    private static final int COST = 1;
    private int MIN_GOLD = 10;
    private int MAX_GOLD = 30;
    private static final int UPGRADE_GOLD = 10;


    public BagOfGold(int upgrades) {
        super(ID, NAME, "cards/bagOfGold.png", COST, DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.RARE, CardTarget.SELF);
        timesUpgraded = upgrades;
        magicNumber = baseMagicNumber = (MIN_GOLD + MAX_GOLD) / 2;
        exhaust = true;
    }

    @Override
    public void upgrade() {
        MIN_GOLD += UPGRADE_GOLD;
        MAX_GOLD += UPGRADE_GOLD;
        timesUpgraded++;
        upgradeMagicNumber(UPGRADE_GOLD);
        upgraded = true;
        name = NAME + "+" + timesUpgraded;
        rawDescription = EXTENDED_DESCRIPTION[0] + MIN_GOLD + EXTENDED_DESCRIPTION[1] + MAX_GOLD + EXTENDED_DESCRIPTION[2];
        initializeTitle();
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ObtainGoldAction(AbstractDungeon.player, (float) Settings.M_W / 2, (float) Settings.M_H / 2, AbstractDungeon.cardRng.random(MIN_GOLD, MAX_GOLD)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BagOfGold(timesUpgraded);
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }
}
