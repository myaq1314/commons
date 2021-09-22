package org.czh.commons.utils.convertor;

import org.czh.commons.entity.IBaseTreeEntity;
import org.czh.commons.entity.resp.ConcreteTreeRespVO;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author : czh
 * description :
 * date : 2021-06-17
 * email 916419307@qq.com
 */
public final class TreeConvertor {

    /*
      -----------------------------bean convert to tree-------------------------------
     */

    /**
     * 集合 转换为 树结构
     *
     * @param source              源集合
     * @param currentTagConvertor 当前节点唯一标识 函数
     * @param parentTagConvertor  父节点唯一标识 函数
     * @param parentTag           顶级节点唯一表示
     * @param <S>                 源集合元素类型
     * @param <K>                 唯一标识元素类型
     * @return 树结构
     */
    public static <S extends IBaseTreeEntity<S>, K> List<S> convertToTree(final Collection<S> source,
                                                                          final Function<S, K> currentTagConvertor,
                                                                          final Function<S, K> parentTagConvertor,
                                                                          final K parentTag) {
        return convertToTree(source, s -> s, currentTagConvertor, parentTagConvertor, parentTag);
    }

    /**
     * 集合 转换为 树结构
     *
     * @param source              源集合
     * @param convertor           转换器
     * @param currentTagConvertor 当前节点唯一标识 函数
     * @param parentTagConvertor  父节点唯一标识 函数
     * @param parentTag           顶级节点唯一表示
     * @param <S>                 源集合元素类型
     * @param <T>                 目标树结构元素类型
     * @param <K>                 唯一标识元素类型
     * @return 树结构
     */
    public static <S, T extends IBaseTreeEntity<T>, K> List<T> convertToTree(final Collection<S> source,
                                                                             final Function<S, T> convertor,
                                                                             final Function<S, K> currentTagConvertor,
                                                                             final Function<S, K> parentTagConvertor,
                                                                             final K parentTag) {
        return convertToTree(source, convertor, currentTagConvertor, parentTagConvertor, null, parentTag);
    }

    /**
     * 集合 转换为 树结构
     *
     * @param source              源集合
     * @param convertor           转换器
     * @param currentTagConvertor 当前节点唯一标识 函数
     * @param parentTagConvertor  父节点唯一标识 函数
     * @param filter              过滤器
     * @param parentTag           顶级节点唯一表示
     * @param <S>                 源集合元素类型
     * @param <T>                 目标树结构元素类型
     * @param <K>                 唯一标识元素类型
     * @return 树结构
     */
    public static <S, T extends IBaseTreeEntity<T>, K> List<T> convertToTree(final Collection<S> source,
                                                                             final Function<S, T> convertor,
                                                                             final Function<S, K> currentTagConvertor,
                                                                             final Function<S, K> parentTagConvertor,
                                                                             final Predicate<S> filter,
                                                                             final K parentTag) {

        if (EmptyValidate.isEmpty(source)) {
            return new ArrayList<>(0);
        }

        EmptyAssert.allNotNull(convertor, currentTagConvertor, parentTagConvertor);

        // 最终输出结果
        List<T> target = new ArrayList<>();
        // 所有节点都要暂存到Map中，方便查找
        Map<K, IBaseTreeEntity<T>> tempMap = new HashMap<>(source.size());

        source.stream().filter(s -> EmptyValidate.isNull(filter) || filter.test(s)).forEach(s -> {
            T t = convertor.apply(s);
            // 获取当前节点唯一标识
            K currentId = currentTagConvertor.apply(s);
            // 缓存Map中存放着虚假值，此处进行替换
            if (tempMap.containsKey(currentId)) {
                t.setChildren(tempMap.get(currentId).getChildren());
            }
            tempMap.put(currentId, t);
            // 获取父节点唯一标识
            K parentId = parentTagConvertor.apply(s);
            // 通过比较，判断当前节点是否属于顶层节点数据
            if (Objects.equals(parentId, parentTag)) {
                target.add(t);
            } else {
                // 缓存Map中不存在父节点数据，那么制造一个虚假值，等真正的节点数据到来时，再替换掉
                if (!tempMap.containsKey(parentId)) {
                    tempMap.put(parentId, new ConcreteTreeRespVO<>());
                }
                IBaseTreeEntity<T> parentNode = tempMap.get(parentId);
                parentNode.addChild(t);
            }
        });
        return target;
    }
}
