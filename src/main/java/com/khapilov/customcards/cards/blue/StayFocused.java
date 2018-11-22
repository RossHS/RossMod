package com.khapilov.customcards.cards.blue;

import basemod.abstracts.CustomCard;
import com.khapilov.customcards.powers.EnergyRecoverPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;

/**
 * @author Ross Khapilov
 * @version 1.0 created on 14.11.2018
 */
public class StayFocused extends CustomCard {
    public static final String ID = "Stay Focused";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 1;

    public StayFocused() {
        super(ID, NAME, "cards/stayFocused.png", COST, DESCRIPTION, CardType.SKILL, CardColor.BLUE, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        baseMagicNumber = p.energy.energy;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FocusPower(p, baseMagicNumber), baseMagicNumber));
        AbstractDungeon.actionManager.addToBottom(new IncreaseMaxOrbAction(baseMagicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EnergyRecoverPower(p, -baseMagicNumber), -baseMagicNumber));
    }

    @Override
    public void applyPowers() {
        baseMagicNumber = AbstractDungeon.player.energy.energy;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
        super.applyPowers();
    }

    @Override
    public void onMoveToDiscard() {
        rawDescription = DESCRIPTION;
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new StayFocused();
    }
}
