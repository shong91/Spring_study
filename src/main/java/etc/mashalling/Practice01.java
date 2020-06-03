package etc.mashalling;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="practice")
@Data
public class Practice01 {
    private String a;

//
//    1. OXM (Object XML Mapping): XML 데이터의 객체 매핑
//    마샬링(mashalling): 특정 데이터를 XML 형태로 만드는 것 (<-> 언마샬링)
//
//    2. JAXB (Java Architecture for XML Binding)
//        마샬링, 언마샬링의 과정을 JAXB Annotation 을 사용하여 직관적으로 매핑할 수 있도록 함
//        주요 어노테이션
//                @XmlAccessorType : XML 데이터를 어떤 방멈으로 매핑할지 선언하는 어노테이션
//                @XmlRootElement : 해당 클래스가 XML 특정 노트의 루트라는 것을 명시
//                @XmlElement : 해당 변수가 XML 의 노드임을 명시
//                @XmlElementWrapper : 해당 변수가 특정 노드들을 감싸고 있는 Wrapper 라는 것을 명시
//


    }
