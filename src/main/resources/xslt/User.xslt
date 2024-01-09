<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>User Information</h2>
                <a href="/user/list">All Users</a>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>ID</th>
                        <th>UUID</th>
                        <th>Username</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Is Deleted</th>
                        <th>Registration Date</th>
                        <th>Last Modified Date</th>
                        <th>Role</th>
                    </tr>
                    <tr>
                        <td><xsl:value-of select="User/id"/></td>
                        <td><xsl:value-of select="User/uuid"/></td>
                        <td><xsl:value-of select="User/username"/></td>
                        <td><xsl:value-of select="User/firstname"/></td>
                        <td><xsl:value-of select="User/lastname"/></td>
                        <td><xsl:value-of select="User/email"/></td>
                        <td><xsl:value-of select="User/isDeleted"/></td>
                        <td><xsl:value-of select="User/registrationDate"/></td>
                        <td><xsl:value-of select="User/lastModifiedDate"/></td>
                        <td><xsl:value-of select="User/role"/></td>
                    </tr>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
