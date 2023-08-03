import * as S from './SelectStyle';
import TrimCard from '../../../components/TrimCard/TrimCard';

function Select({ nextPage }) {
  return (
    <S.Select>
      <S.Title>트림을 선택해주세요</S.Title>
      <S.Trim>
        <TrimCard
          name="Exclusive"
          description="기본에 충실한 팰리세이드"
          price="4,044,000"
          nextPage={nextPage}
        />
        <TrimCard
          name="Le Blanc"
          description="기본에 충실한 팰리세이드"
          price="4,044,000"
          nextPage={nextPage}
        />
        <TrimCard
          name="Prestige"
          description="기본에 충실한 팰리세이드"
          price="4,044,000"
          nextPage={nextPage}
        />
        <TrimCard
          name="Calligraphy"
          description="기본에 충실한 팰리세이드"
          price="4,044,000"
          nextPage={nextPage}
        />
      </S.Trim>
    </S.Select>
  );
}

export default Select;
