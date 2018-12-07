package com.khapilov.customcards.cards.green;

import basemod.abstracts.CustomCard;
import com.khapilov.customcards.powers.PoisonArmorPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * @author Ross Khapilov
 * @version 1.0 02.12.2018
 */
public class PoisonedArmor extends CustomCard {
    public static final String ID = "Poisoned Armor";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int BLOCK = 5;
    private static final int POISON = 3;
    private static final int COST = 1;

    public PoisonedArmor() {
        super(ID, NAME, "cards/poisonedArmor.png", COST, DESCRIPTION, CardType.SKILL, CardColor.GREEN, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = POISON;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(3);
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PoisonArmorPower(p, magicNumber), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PoisonedArmor();
    }
}
