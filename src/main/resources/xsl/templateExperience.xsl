<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template name="experienceTemplate" match="listeExperience">

        <xsl:for-each select="experience">

            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="enteteColonne">
                                <xsl:value-of select="client"/>
                            </div>
                            <div class="enteteColonne">
                                <xsl:value-of select="localisation"/>
                            </div>
                        </div>
                        <div class="col-md-6 col-left">
                            <div class="enteteColonne">du
                                <xsl:value-of select="dateDebut"/>
                                au
                                <xsl:value-of select="dateFin"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <xsl:for-each select="description">
                        <p>
                            <xsl:value-of select="." disable-output-escaping="yes"/>
                        </p>
                    </xsl:for-each>
                </div>
                <div class="panel-footer">
                    <xsl:for-each select="pileLogiciel">
                        <xsl:value-of select="."/>
                        <xsl:if test="position() != last()">,&#160;</xsl:if>
                    </xsl:for-each>
                </div>
            </div>
        </xsl:for-each>

    </xsl:template>

</xsl:stylesheet>