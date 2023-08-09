var objEncrypt	= new Object();
objEncrypt.ttangAES = new ttangAES(); 

function ttangAES(){}
ttangAES.prototype.keySize = 128;
ttangAES.prototype.iterationCount = 2000;
ttangAES.prototype.passPhrase = "ttang.com";
ttangAES.prototype.iv = "";
ttangAES.prototype.salt = "";
ttangAES.prototype.init = function () 
{
	this.iv = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
	this.salt = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
}
ttangAES.prototype.encrypt = function ( p_text ) 
{
    // PBKDF2 키 생성 
    var key128Bits100Iterations =  CryptoJS.PBKDF2(this.passPhrase, CryptoJS.enc.Hex.parse(this.salt), { keySize: this.keySize/32, iterations: this.iterationCount });  
    
    //암호화
    var retValue = CryptoJS.AES.encrypt( p_text, key128Bits100Iterations, { iv: CryptoJS.enc.Hex.parse(this.iv) }).toString();    
	return retValue;
};