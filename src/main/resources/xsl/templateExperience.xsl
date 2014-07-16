<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template name="experienceTemplate" match="listeExperience">

        <xsl:for-each select="experience">
            <div class="entete">
                <div class="enteteColonne">
                    <xsl:value-of select="client"/>
                </div>
                <div class="enteteColonne">
                    <xsl:value-of select="localisation"/>
                </div>
                <div class="enteteColonne">du
                    <xsl:value-of select="dateDebut"/>
                    au
                    <xsl:value-of select="dateFin"/>
                </div>
            </div>
            <div class="experience">
                <div class="presentation">
                    <xsl:for-each select="description">
                        <p>
                            <xsl:value-of select="." disable-output-escaping="yes"/>
                        </p>
                    </xsl:for-each>
                </div>
                <div class="technique">
                        <xsl:for-each select="pileLogiciel">
                                <xsl:value-of select="."/>
                                <xsl:if test="position() != last()">,&#160;</xsl:if>
                        </xsl:for-each>
                </div>
            </div>
        </xsl:for-each>

    </xsl:template>

</xsl:stylesheet>