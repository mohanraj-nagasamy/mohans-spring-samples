class TemplaterContext {
    def packageName;
    def domain;
    def fields;  
    def classlike(s) {s[0].toUpperCase() + (s.size()<2 ? '' : s[1..-1])}
    def beanlike(s) {s[0].toLowerCase() + (s.size()<2 ? '' : s[1..-1])}    
}